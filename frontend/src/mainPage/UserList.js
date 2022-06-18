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
        axios.get(`http://localhost:8989/chat/createConversation?receiverUserId=${userData.id}`, {
            headers: { Authorization: localStorage.getItem("Authorization") }
        }).then((res) => {
           console.log(res.data);
            if(res.data.status===200 || res.data.status===409){
                localStorage.setItem("conversationId",res.data.data.conversationId);
            }
        });
    }

    return<>
         {data.map((user) => (
            <ListItem button key={user.id} onClick={()=>getIdOnClick(user)}  >
            <ListItemIcon>
            <Avatar style={{textTransform: 'capitalize'}} alt={user.firstName} src="logo.jpg" />
            </ListItemIcon>
            <ListItemText style={{textTransform: 'capitalize'}} primary={user.firstName} />
            </ListItem>
         ))}

    
    </>
}

