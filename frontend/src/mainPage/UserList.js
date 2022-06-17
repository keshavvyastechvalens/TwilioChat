import React, { useEffect, useState } from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import { Avatar } from '@material-ui/core';
import api from '../Axios_instance/Axios_Instance';




export default function UserList(){
    const [userData, setUserData] = useState([])
    const getData=async()=>
    {
      let response= await api.get("/users")
      setUserData(response.data);
    }
    useEffect(()=>
    {
      getData()
    },[])
    return<>
         {userData.map((user) => (
            <ListItem button>
            <ListItemIcon>
            <Avatar alt="Remy Sharp" src="logo.jpg" />
            </ListItemIcon>
            <ListItemText primary={user.name} />
            </ListItem>
         ))}

    
    </>
}

