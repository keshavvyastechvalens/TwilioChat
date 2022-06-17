import React, { useEffect, useState } from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import { Avatar } from '@material-ui/core';
import { useSelector } from 'react-redux';
import axios from 'axios';





export default function UserList(){
    const [userData, setUserData] = useState([])
   
    const data= useSelector((state)=>state.allProducts.Product)
    function handleClick(){
        alert("pass")
    }

    function getIdOnClick(userData){
        
        console.log(userData.id);

        var myId = localStorage.getItem("Authorization");
        console.log(myId);

        axios.post(`localhost:8989/chat/createConversation?receiverUserId=${userData.id}`)

        
        
     


    }
 
    
    return<>
         {data.map((user) => (
            <ListItem button key={user.id} onClick={()=>getIdOnClick(user)}  >
            <ListItemIcon>
            <Avatar alt="Remy Sharp" src="logo.jpg" />
            </ListItemIcon>
            <ListItemText primary={user.firstName} />
            </ListItem>
         ))}

    
    </>
}

