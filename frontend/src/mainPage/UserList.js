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
            // const messages = await channel.getMessages();
           console.log("--------*-*-*-*-*-*-*--*-*-**---*-*-*-*--*-*--*-");
           
           
           
           channel.getMessages().then(function(messages) {
               const totalMessages = messages.items.length;
               for (var i = 0; i < totalMessages; i++) {
                   const message = messages.items[i];
                   console.log('Author:' + message);
                }
                console.log('Total Messages:' + totalMessages);
            });
          
           console.log("--------*-*-*-*-*-*-*--*-*-**---*-*-*-*--*-*--*-");
           console.log("--------*-*-*-*-*-*-*--*-*-**---*-*-*-*--*-*--*-");
           console.log("--------*-*-*-*-*-*-*--*-*-**---*-*-*-*--*-*--*-");
           console.log("--------*-*-*-*-*-*-*--*-*-**---*-*-*-*--*-*--*-");
           console.log("--------*-*-*-*-*-*-*--*-*-**---*-*-*-*--*-*--*-");

            console.log(messageTest);

        });
        try {
            console.log("before")
            const channel1 = await clientTest.getChannelByUniqueName(channelName1);
            console.log("111111111111111111111", channel1.channelState.status);
            console.log("1111111111111111111111111111111111111111111111111111111111111111");

            if (channel1.channelState.status !== "joined") {
            console.log("11222222222222222222222222222222222222222222222222222222222222");

               var res1 = await channel1.join();
            console.log("333333333333333333333333333333333333333333333333333333333333333");
           setChannelTest(res1);
            }

            
            console.log("114444444444444444444444444444444444444444444444444444444444444444444444444");

            channel1.on("messageAdded", (message) => {
            console.log("1555555555555555555555555");

                console.log("-------11", message);

                const lastMessageAdd = messageTest.push(message);
                console.log("after pushing before inserting", lastMessageAdd);
                setMessageTest(
                    lastMessageAdd
                )
                console.log("finalres", messageTest);
            console.log("166666666666666666666666666");
            }
            );
            console.log("77777777777777777777777777777777777777777777");

          
            console.log("88888888888888888888888888888888888888888888");
        }
        catch {


            try {
                const channel2 = await clientTest.getChannelByUniqueName(channelName2);
                console.log("22222222222222222222222222", channel2.channelState.status);
                if (channel2.channelState.status !== "joined") {
                    var res2 = await channel2.join();
                    setChannelTest(res2);
                }
                
                channel2.on("messageAdded", (message) => {
                    console.log("-------11", message);
                    const lastMessageAdd = messageTest.push(message);
                    console.log("after pushing before inserting", lastMessageAdd);
                    setMessageTest(
                        lastMessageAdd
                    )
                    console.log("finalres", messageTest);

                }
                );
            }
            catch {
                try {
                    console.log("333333333333333333333333333----------------------------");
                    const channel3 = await clientTest.createChannel({
                        uniqueName: channelName1,
                        friendlyName: channelName1,
                    });
                    const res3 = await channel3.join();
                    channel3.on("messageAdded", (message) => {
                        console.log("-------11", message);

                        const lastMessageAdd = messageTest.push(message);
                        console.log("after pushing before inserting", lastMessageAdd);
                        setMessageTest(
                            lastMessageAdd
                        )
                        console.log("finalres", messageTest);
                    });

                    setChannelTest(res3);

                } catch {
                    throw new Error("unable to create channel, please reload this page");
                }
            }
        }

    }



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

