import React, { useEffect, useState } from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
// import ListSubheader from '@material-ui/core/ListSubheader';
import DashboardIcon from '@material-ui/icons/Dashboard';
// import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
// import PeopleIcon from '@material-ui/icons/People';
// import BarChartIcon from '@material-ui/icons/BarChart';
// import LayersIcon from '@material-ui/icons/Layers';
// import AssignmentIcon from '@material-ui/icons/Assignment';
import axios from 'axios'
import { Avatar } from '@material-ui/core';




export default function UserList(){
    const [userData, setUserData] = useState([])
    const getData=async()=>
    {
      let response= await axios.get("https://jsonplaceholder.typicode.com/users")
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

