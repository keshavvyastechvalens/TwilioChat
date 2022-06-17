import React, { useEffect, useState } from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { useFormik } from 'formik';
import * as Yup from 'yup'
import Swal from 'sweetalert2'
import api from '../Axios_instance/Axios_Instance';
import axios from 'axios';
import {useNavigate} from 'react-router-dom'




const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(3),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

export default function SIgnUp() {
  const classes = useStyles();
  const navigate=useNavigate()
  const [data,setData]=useState({ firstName:'',
  lastName:'',
  emailId:'',
  contactNo:'',
  password:'',
  confirm_password:''})


  const phoneRegExp = /^((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?$/
  const formik= useFormik({
    initialValues:{
        firstName:'',
        lastName:'',
        emailId:'',
        contactNo:'',
        password:'',
        userName:''
    },

    validationSchema:Yup.object({
        firstName:Yup.string().required("Required!"),
        lastName:Yup.string().required("Required!"),
        contactNo:Yup.string().matches(phoneRegExp, 'Phone number is not valid').required("Required!"),
        emailId:Yup.string().email("invalid Email Address").required("Required!"),
        userName:Yup.string().required("Required!"),
        password:Yup.string().required("Required!"),
        
    }),
    onSubmit: async (values)=>{

      
        console.log(values);
        // await fetch('localhost:8989/user/registeruser',{
        //     method:"POST",
        //     headers:{"Content-Type":"application/json"},
        //     body:JSON.stringify(values)
            
        // }
        // )
        // const response= await api.post("localhost:8989/user/registeruser")
        // console.log('response',response);
        const res = await axios.post("http://localhost:8989/user/registeruser",values);
          console.log(res);
            Swal.fire(
              'Registration Successful ',
              'You clicked the button!',
              'success'
            )
            navigate('/signin')

            }
       
      
       
    // }
})



    return (<>

<div width="100vw" className="row">
   {/* <caption><h3>Validation From Formik and yup </h3></caption> */}

   </div>
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign up
        </Typography>
        <form onSubmit={formik.handleSubmit} className={classes.form} noValidate>
        <Grid container spacing={2}>
        <Grid item xs={12} sm={6}>
              <TextField variant="outlined" onBlur={formik.handleBlur} value={formik.values.firstName} onChange={formik.handleChange} required fullWidth id="firstName" label="First Name" name="firstName" autoComplete="fname"/>
              {formik.touched.firstName && formik.errors.firstName ? <p style={{color:'red'}}>{formik.errors.firstName}</p> : null}
            </Grid>


            <Grid item xs={12} sm={6}>
              <TextField variant="outlined" onBlur={formik.handleBlur} value={formik.values.lastName} onChange={formik.handleChange} required fullWidth id="lastName" label="Last Name" name="lastName" autoComplete="lname"/>
              {formik.touched.lastName && formik.errors.lastName ? <p style={{color:'red'}}>{formik.errors.lastName}</p> : null}
            </Grid>


            <Grid item xs={12}>
              <TextField variant="outlined" onBlur={formik.handleBlur} value={formik.values.emailId} onChange={formik.handleChange} required fullWidth id="emailId" label="Email Address" name="emailId" autoComplete="email"  />
              {formik.touched.emailId && formik.errors.emailId ? <p style={{color:'red'}}>{formik.errors.emailId}</p> : null}
            </Grid>


            <Grid item xs={12}>
              <TextField variant="outlined" onBlur={formik.handleBlur} value={formik.values.contactNo} onChange={formik.handleChange} required fullWidth id="contactNo" label="contactNo number" name="contactNo" autoComplete="contactNo"  />
              {formik.touched.contactNo && formik.errors.contactNo ? <p style={{color:'red'}}>{formik.errors.contactNo}</p> : null}
            </Grid>

            <Grid item xs={12}>
           <TextField onBlur={formik.handleBlur} onChange={formik.handleChange} value={formik.values.userName} name="userName" id="userName"   fullWidth label="User Name" variant="outlined" />
             {formik.touched.userName && formik.errors.userName? <div className='error' > {formik.errors.userName} </div>:null}
           </Grid>


            <Grid item xs={12}>
              <TextField variant="outlined" onBlur={formik.handleBlur} value={formik.values.password} onChange={formik.handleChange} required fullWidth name="password" label="Password" type="password" id="password" autoComplete="current-password" />
              {formik.touched.password && formik.errors.password ? <p style={{color:'red'}}>{formik.errors.password}</p> : null}
            </Grid>

            
          


        </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Sign Up
          </Button>
          
          <Grid container justifyContent="flex-end">
            <Grid item>
              <Link href="/signin" variant="body2">
                Already have an account? Sign in
              </Link>
              
            </Grid>
          </Grid>
        </form>
      </div>
     
    </Container>
 </> );
}