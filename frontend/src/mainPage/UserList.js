import React, { useEffect, useState } from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import { Avatar } from '@material-ui/core';
import { useSelector } from 'react-redux';





export default function UserList(){
    const [userData, setUserData] = useState([])
   
    const data= useSelector((state)=>state.allProducts.Product)
    
    return<>
         {data.map((user) => (
            <ListItem button key={user.id}>
            <ListItemIcon>
            <Avatar alt="Remy Sharp" src="logo.jpg" />
            </ListItemIcon>
            <ListItemText primary={user.firstName} />
            </ListItem>
         ))}

    
    </>
}

