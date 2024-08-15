import React, { useContext, useEffect, useState } from 'react';
import { Box, Typography, TextField, Button, Dialog, DialogActions, DialogContent, DialogTitle, Grid, Paper, Card, CardContent, Collapse } from '@mui/material';
import Sidebar from './EmployeeSidebar';
import axios from 'axios';
import { UserContext } from "../UserContext/UserContext";

const ApplyLeave = () => {
    const { user } = useContext(UserContext);
    const storedUser = user.userDetails;
    const [formData, setFormData] = useState({
        reason: '',
        startDate: '',
        endDate: ''
    });
    const [leave, setLeave] = useState([]);
    const [errors, setErrors] = useState({});
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [expandedLeaveId, setExpandedLeaveId] = useState(null);
    const [showLeaveStatus, setShowLeaveStatus] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const validate = () => {
        let isValid = true;
        const newErrors = {};

        if (!formData.reason.trim()) {
            newErrors.reason = 'Reason is required';
            isValid = false;
        }
        if (!formData.startDate) {
            newErrors.startDate = 'Start date is required';
            isValid = false;
        }
        if (!formData.endDate) {
            newErrors.endDate = 'End date is required';
            isValid = false;
        } else if (new Date(formData.endDate) < new Date(formData.startDate)) {
            newErrors.endDate = 'End date must be after start date';
            isValid = false;
        }

        setErrors(newErrors);
        return isValid;
    };

    const handleLeaveStatus = async () => {
        try {
            const res = await axios.get(`http://localhost:8080/leave/user/${user.userDetails.id}`);
            setLeave(res.data);
        } catch (error) {
            console.error('Error fetching leave status:', error);
        }
    };

    useEffect(() => {
        handleLeaveStatus();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (validate()) {
            try {
                await axios.post("http://localhost:8080/leave/apply", formData, {
                    params: {
                        userId: storedUser.id
                    }
                });
                setIsModalOpen(true);
                handleLeaveStatus(); // Refresh leave status after submission
            } catch (error) {
                console.error("Error applying for leave:", error);
            }
        }
    };

    const closeModal = () => {
        setIsModalOpen(false);
    };

    const toggleExpanded = (leaveId) => {
        setExpandedLeaveId(expandedLeaveId === leaveId ? null : leaveId);
    };

    const toggleShowLeaveStatus = () => {
        setShowLeaveStatus(!showLeaveStatus);
    };

    return (
        <Box sx={{ display: 'flex', height: '100vh' }}>
            <Sidebar />
            <Box 
                sx={{ 
                    flexGrow: 1, 
                    p: 3, 
                    bgcolor: '#e0e0e0', 
                    overflowY: 'auto',
                }}>
                <Paper elevation={3} sx={{ 
                    padding: 3, 
                    bgcolor: 'white', 
                    maxWidth: '600px', 
                    margin: '0 auto', 
                    borderRadius: '8px',
                    opacity: 0.9 // Added opacity to Paper
                }}>
                    <Typography variant="h4" align="center" gutterBottom sx={{ color: 'black' }}>
                        Leave Application
                    </Typography>
                    <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <TextField
                                    fullWidth
                                    multiline
                                    rows={4}
                                    id="reason"
                                    name="reason"
                                    label="Reason"
                                    variant="outlined"
                                    value={formData.reason}
                                    onChange={handleChange}
                                    error={Boolean(errors.reason)}
                                    helperText={errors.reason}
                                    sx={{
                                        bgcolor: 'white',
                                        '& .MuiInputBase-input': {
                                            color: 'black'
                                        },
                                        '& .MuiInputLabel-root': {
                                            color: 'black'
                                        },
                                        '& .MuiOutlinedInput-root': {
                                            '& fieldset': {
                                                borderColor: 'black'
                                            },
                                            '&:hover fieldset': {
                                                borderColor: 'black'
                                            },
                                            '&.Mui-focused fieldset': {
                                                borderColor: 'black'
                                            }
                                        }
                                    }}
                                />
                            </Grid>
                            <Grid item xs={12} md={6}>
                                <Typography variant="body1" sx={{ color: 'black' }}>Start Date</Typography>
                                <TextField
                                    fullWidth
                                    id="startDate"
                                    name="startDate"
                                    type="date"
                                    variant="outlined"
                                    InputLabelProps={{ shrink: true }}
                                    value={formData.startDate}
                                    onChange={handleChange}
                                    error={Boolean(errors.startDate)}
                                    helperText={errors.startDate}
                                    sx={{
                                        bgcolor: 'white',
                                        '& input': {
                                            color: 'black',
                                            paddingTop: '16px'
                                        },
                                        '& label': {
                                            color: 'black'
                                        }
                                    }}
                                />
                            </Grid>
                            <Grid item xs={12} md={6}>
                                <Typography variant="body1" sx={{ color: 'black' }}>End Date</Typography>
                                <TextField
                                    fullWidth
                                    id="endDate"
                                    name="endDate"
                                    type="date"
                                    variant="outlined"
                                    InputLabelProps={{ shrink: true }}
                                    value={formData.endDate}
                                    onChange={handleChange}
                                    error={Boolean(errors.endDate)}
                                    helperText={errors.endDate}
                                    sx={{
                                        bgcolor: 'white',
                                        '& input': {
                                            color: 'black',
                                            paddingTop: '16px'
                                        },
                                        '& label': {
                                            color: 'black'
                                        }
                                    }}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <Button
                                    type="submit"
                                    variant="contained"
                                    color="primary"
                                    sx={{ mt: 2 }}
                                >
                                    Submit Leave Request
                                </Button>
                            </Grid>
                        </Grid>
                    </Box>
                </Paper>

                <Button
                    variant="contained"
                    onClick={toggleShowLeaveStatus}
                    sx={{ bgcolor: 'primary.main', color: 'white', mt: 4 }}
                >
                    {showLeaveStatus ? "Hide Status" : "Show Status"}
                </Button>

                {showLeaveStatus && (
                    <Grid container spacing={2} sx={{ mt: 4 }}>
                        {leave.map((item) => (
                            <Grid item xs={12} sm={6} md={4} key={item.id}>
                                <Card sx={{ 
                                    bgcolor: 'white', 
                                    borderRadius: '8px',
                                    opacity: 0.9 // Added opacity to Card
                                }}>
                                    <CardContent>
                                        <Typography variant="h6" sx={{ color: 'black', marginBottom: '8px' }}>
                                            Status: {item.status}
                                        </Typography>
                                        <Button
                                            variant="contained"
                                            onClick={() => toggleExpanded(item.id)}
                                            sx={{ bgcolor: 'primary.main', color: 'white', mt: 1 }}
                                        >
                                            {expandedLeaveId === item.id ? "Hide Details" : "Show Details"}
                                        </Button>
                                        <Collapse in={expandedLeaveId === item.id}>
                                            <Box sx={{ mt: 2 }}>
                                                <Typography variant="body1" sx={{ color: item.status === 'Approved' ? 'green' : item.status === 'Denied' ? 'red' : 'black', marginBottom: '4px' }}>
                                                    Reason: {item.reason}
                                                </Typography>
                                                <Typography variant="body2" sx={{ color: 'black', marginBottom: '4px' }}>
                                                    Start Date: {new Date(item.startDate).toLocaleDateString()}
                                                </Typography>
                                                <Typography variant="body2" sx={{ color: 'black' }}>
                                                    End Date: {new Date(item.endDate).toLocaleDateString()}
                                                </Typography>
                                            </Box>
                                        </Collapse>
                                    </CardContent>
                                </Card>
                            </Grid>
                        ))}
                    </Grid>
                )}

                <Dialog open={isModalOpen} onClose={closeModal}>
                    <DialogTitle>Success</DialogTitle>
                    <DialogContent>
                        <Typography variant="body1">Your leave request has been submitted successfully!</Typography>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={closeModal} color="primary">
                            Close
                        </Button>
                    </DialogActions>
                </Dialog>
            </Box>
        </Box>
    );
};

export default ApplyLeave;
