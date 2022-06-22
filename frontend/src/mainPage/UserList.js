import React, { useContext, useEffect, useState } from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import { Avatar } from '@material-ui/core';
import { useSelector } from 'react-redux';
import axios from 'axios';
import { Client } from '@twilio/conversations';
import { ChannelContext } from '../App';





export default function UserList() {
    // const [userData, setUserData] = useState([])

    const data = useSelector((state) => state.allProducts.Product)
    const [channelTest, setChannelTest, clientTest, setClientTest, messageTest, setMessageTest] = useContext(ChannelContext);

    var channelTestName1;
    var channelTestName2;
    var channelName1;
    var channelName2;
    function getIdOnClick(user) {
        channelTestName2 = user.userName.concat(localStorage.getItem("login_name"));
        channelTestName1 = localStorage.getItem("login_name") + user.userName;
        channelName1 = channelTestName1.toLowerCase();
        channelName2 = channelTestName2.toLowerCase();

        clientTest.on("tokenAboutToExpire", async () => {
            const response = await axios.get("http://localhost:8989/chat/token", { headers: { "Authorization": localStorage.getItem("Authorization") } })
            localStorage.setItem("twilio_access_token", response.data)
            clientTest.updateToken(response.data);
        });

        clientTest.on("tokenExpired", async () => {
            const response = await axios.get("http://localhost:8989/chat/token", { headers: { "Authorization": localStorage.getItem("Authorization") } })
            localStorage.setItem("twilio_access_token", response.data)
            clientTest.updateToken(response.data);
        });
        joinChannel();

    }


    const joinChannel = async () => {
        clientTest.on("channelJoined", async (channel) => {
            const messages = await channel.getMessages();
            console.log(messages);
            setMessageTest( messages.items || [] )
        });
        try {
            const channel1 = await clientTest.getChannelByUniqueName(channelName1);
            console.log("111111111111111111111",channel1.channelState.status);
            
            if (channel1.channelState.status !== "joined") {
                var res1 = await channel1.join();
              }
            channel1.on("messageAdded", (message) => {
                console.log("-------11",message);

                const lastMessageAdd = messageTest.push(message);
                console.log("after pushing before inserting",lastMessageAdd);
                setMessageTest(
                   lastMessageAdd
                )
                console.log("finalres",messageTest);
            }
            );
          

            setChannelTest(res1);
        }
        catch {


            try {
                const channel2 = await clientTest.getChannelByUniqueName(channelName2);
                console.log("22222222222222222222222222",channel2.channelState.status);
                if (channel2.channelState.status !== "joined") {
                    var res2 = await channel2.join();
                  }
                channel2.on("messageAdded", (message) => {
                    console.log("-------11",message);
                    const lastMessageAdd = messageTest.push(message);
                console.log("after pushing before inserting",lastMessageAdd);
                setMessageTest(
                   lastMessageAdd
                )
                console.log("finalres",messageTest);

                }
                );
                setChannelTest(res2);
            }
            catch {
                try {
                    const channel3 = await clientTest.createChannel({
                        uniqueName: channelName1,
                        friendlyName: channelName1,
                    });
                    const res3 = await channel3.join();
                    channel3.on("messageAdded", (message) => {
                    console.log("-------11",message);
                        
                        const lastMessageAdd = messageTest.push(message);
                        console.log("after pushing before inserting",lastMessageAdd);
                        setMessageTest(
                           lastMessageAdd
                        )
                        console.log("finalres",messageTest);
                    });

                    setChannelTest(res3);

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

