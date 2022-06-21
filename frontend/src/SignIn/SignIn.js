import { Button, Grid, TextField, Typography } from '@material-ui/core'
import React from 'react'
import './SignIn.scss'
import {useFormik} from 'formik'
import * as Yup from 'yup'
import { Link, useNavigate } from 'react-router-dom'
import axios from 'axios'
import {useDispatch} from 'react-redux'
import { setProduct } from '../Redux/Action/Action'





export default function SignIn() {

  const navigate= useNavigate()
  const dispatch = useDispatch()
  const initialValues={
   
    userName:'',
    password:'',
  
}               
const onSubmit=async values=>{
    
  
  dispatch(setProduct("res.data.data.userList111111"))
   const res = await axios.post("http://localhost:8989/user/login",values);
  
   if(res.data.status===200)
   {
    localStorage.setItem("login_name",res.data.data.userName);

    localStorage.setItem("Authorization",res.data.data.token);
    navigate('/dashboard',{state:{name:res.data.data}})
  
    dispatch(setProduct(res.data.data.userList))

    
   }
   else{
     alert("Error")
     
   }
  
    
}

const validationSchema=Yup.object({
   
   
  userName:Yup.string().required("Required.!"),
    password:Yup.string().required("Required.!").required("Required.!"),
   
})


const formik= useFormik(
    {
        initialValues,          
        validationSchema,
        onSubmit    
    }
)

  return (
    <div className='main'>
      <div className='subdiv'>
        <form  autoComplete="off" onSubmit={formik.handleSubmit}>
        <Grid container spacing={2}>
         <Grid item xs={12}>
         <Typography component="h1" variant="h5" className='center'>
          Sign In
        </Typography>
         </Grid>
         
          <Grid item xs={12}>
            <TextField onBlur={formik.handleBlur} onChange={formik.handleChange} value={formik.values.userName} fullWidth name="userName" id="userName" label="User Name" variant="outlined" />
            {formik.touched.userName && formik.errors.userName? <div className='error' > {formik.errors.userName} </div>:null}
          </Grid>
          
          <Grid item xs={12}>
            <TextField onBlur={formik.handleBlur} type="password" onChange={formik.handleChange} value={formik.values.password} name="password" id="password" fullWidth label="Password" variant="outlined" />
            {formik.touched.password && formik.errors.password? <div className='error' > {formik.errors.password} </div>:null}
          </Grid>
          
          <Grid item xs={12}>
         
          <Button fullWidth type="submit" variant="contained" color="primary">

              Login
          </Button> 
          
          </Grid>
          <Grid item xs={12}>
          <Link to='/signup'>
            <Typography component="h3" variant="h6" className='center'>
              Don't have an account? Sign Up
           </Typography>
           </Link>
          </Grid>

        </Grid>
        </form>
      </div>
     </div>
  )
}
