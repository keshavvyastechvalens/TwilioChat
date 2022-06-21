import React, { useEffect, useState } from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import { Avatar } from '@material-ui/core';
import { useSelector } from 'react-redux';
import axios from 'axios';
import { Client } from '@twilio/conversations';





export default function UserList() {
    // const [userData, setUserData] = useState([])

    const data = useSelector((state) => state.allProducts.Product)
    const client_response = useSelector((state) => state.allProducts.client)

    console.log('client_response', client_response);
    var channelName1;
    var channelName2;
    function getIdOnClick(user) {
        // alert("pass",user)
        console.log("-------------------", user);
        channelName1 = user.userName.concat(localStorage.getItem("login_name"));
        channelName2 = localStorage.getItem("login_name") + user.userName;
        console.log("one------", channelName1);
        console.log("one------", channelName2);


        client_response.on("tokenAboutToExpire", async () => {
            // const token = await this.getToken(email);
            const response = await axios.get("http://localhost:8989/chat/token", { headers: { "Authorization": localStorage.getItem("Authorization") } })
            localStorage.setItem("twilio_access_token", response.data)
            client_response.updateToken(response.data);
        });

        client_response.on("tokenExpired", async () => {
            const response = await axios.get("http://localhost:8989/chat/token", { headers: { "Authorization": localStorage.getItem("Authorization") } })
            localStorage.setItem("twilio_access_token", response.data)
            client_response.updateToken(response.data);
        });
        joinChannel();

    }
    //----------------------------------------------------------------------------------------- 


    const joinChannel = async () => {
        client_response.on("channelJoined", async (channel) => {
            // getting list of all messages since this is an existing channel
            const messages = await channel.getMessages();
        });
        console.log("11111");
        try {
            const channel = await client_response.getChannelByUniqueName(channelName1);
            // await this.joinChannel(channel);
            await channel.join();

            console.log("2222");

        }
        catch {
            try {
                const channel = await client_response.getChannelByUniqueName(channelName2);
                await channel.join();
                console.log("33333");

            }
            catch {
                try {
                    const channel = await client_response.createChannel({
                        uniqueName: channelName1,
                        friendlyName: channelName1,
                    });
                    // await this.joinChannel(channel);
                    const res = await channel.join();
                    console.log("444444----",res);
                    console.log("44444")
                } catch {
                    throw new Error("unable to create channel, please reload this page");
                }
            }
        }

    }





    // function getIdOnClick(userData){

    //     axios.get(`http://localhost:8989/chat/createConversation?receiverUserId=${userData.id}`, {
    //         headers: { Authorization: localStorage.getItem("Authorization") }
    //     }).then((res) => {
    //         setUserData(res.data);
    //         if(res.data.status===200 || res.data.status===409){
    //             localStorage.setItem("conversationId",res.data.data.conversationId);
    //         }

    //     });


    // }

    // console.log("Receiver",userData.data.receiver.userName);
    // console.log("Sender",userData.data.sender.userName);
    // console.log(userData.data.receiver.userName+userData.data.sender.userName);
    // const createChannel=userData
    // console.log(userData.data.receiver.userName); //sender
    // console.log(createChannel.data.sender.userName);
    // console.log(userData.data.receiver.userName+userData.data.sender.userName);


    return <>
        {data.map((user) => (
            <ListItem button key={user.id} onClick={() => getIdOnClick(user)}  >
                <ListItemIcon>
                    <Avatar style={{ textTransform: 'capitalize' }} alt={user.firstName} src="logo.jpg" />
                </ListItemIcon>
                <ListItemText style={{ textTransform: 'capitalize' }} primary={user.firstName} />
            </ListItem>
        ))}


    </>
}

