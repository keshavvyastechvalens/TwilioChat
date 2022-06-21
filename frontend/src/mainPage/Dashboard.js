import React, { useEffect, useState } from 'react';
import clsx from 'clsx';
import { makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import Drawer from '@material-ui/core/Drawer';
import Box from '@material-ui/core/Box';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import Badge from '@material-ui/core/Badge';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import NotificationsIcon from '@material-ui/icons/Notifications';
import ListItemText from '@material-ui/core/ListItemText';
import Copyright from './Copyright';
import axios from 'axios';
import { Avatar, ListItem, ListItemAvatar, ListItemIcon, TextField } from '@material-ui/core';
import SendIcon from '@material-ui/icons/Send';
import UserList from './UserList';


const Chat = require("twilio-chat");






const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  toolbar: {
    paddingRight: 24, // keep right padding when drawer closed
    backgroundColor: '#6b5b95'
  },
  toolbarIcon: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: '0 8px',
    ...theme.mixins.toolbar,
  },
  appBar: {
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
  },
  appBarShift: {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  menuButton: {
    marginRight: 36,
  },
  menuButtonHidden: {
    display: 'none',
  },
  title: {
    flexGrow: 1,
  },
  drawerPaper: {
    position: 'relative',
    whiteSpace: 'nowrap',
    width: drawerWidth,
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  drawerPaperClose: {
    overflowX: 'hidden',
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    width: theme.spacing(7),
    [theme.breakpoints.up('sm')]: {
      width: theme.spacing(9),
    },
  },
  appBarSpacer: theme.mixins.toolbar,
  content: {
    flexGrow: 1,
    height: '100vh',
    overflow: 'auto',
  },
  container: {
    paddingTop: theme.spacing(4),
    paddingBottom: theme.spacing(4),
  },
  paper: {
    padding: theme.spacing(2),
    display: 'flex',
    overflow: 'auto',
    flexDirection: 'column',
  },
  fixedHeight: {
    height: 'auto',
  },
}));















export default function Dashboard(props) {

  const classes = useStyles();
  const [open, setOpen] = React.useState(false);
  const [message, setMessage] = useState([]);
  const location =useLocation()
  const [name, setName] = useState("")
  // console.log("location:--- ",location.state.name.userName);
  // setName(location.state.name.userName)
  

  const handleDrawerOpen = () => {
    setOpen(true);
  };
  const handleDrawerClose = () => {
    setOpen(false);
  };


  const sendMessage = async () => {
    let obj = {
      "conversationId": localStorage.getItem("conversationId"),
      "messageContant": document.getElementById("standard-full-width").value
    }
    // headers: {Authorization: localStorage.getItem("Authorization") }
    const res = await axios.post(`http://localhost:8989/chat/sendMessage`, obj, {
      headers: { Authorization: localStorage.getItem("Authorization") }
    }).then((res) => {
      console.log(res);
      const resp = axios.get(`http://localhost:8989/chat/fetchMessage?conversationId=${localStorage.getItem("conversationId")}`, {
        headers: { Authorization: localStorage.getItem("Authorization") }
      }).then((res) => {
        // console.log(res);
        if (res.data.status === 200) {
          let dta = res.data.data || [];
          { dta.sort((a, b) => (a.index > b.index) ? 1 : -1) }
          setMessage(dta);
        }
      });
    });
    document.getElementById("standard-full-width").value = "";
  }



  const createClient =async()=>
  {
    const  token= localStorage.getItem("twilio_access_token")
    const client= await Chat.Client.create(token);
    console.log( "client response-----",client);
  }
  
const getToken= async()=>
{
  const response= await axios.get("http://localhost:8989/chat/token",{headers:{"Authorization":localStorage.getItem("Authorization")}}) 
  localStorage.setItem("twilio_access_token", response.data)
}


  useEffect(()=>
  {
    getToken()
    createClient()
  },[])

  

  const fixedHeightPaper = clsx(classes.paper, classes.fixedHeight);

  return (
    <div className={classes.root}>
      <CssBaseline />
      <AppBar position="absolute" className={clsx(classes.appBar, open && classes.appBarShift)}>
        <Toolbar className={classes.toolbar}>
          <IconButton
            edge="start"
            color="inherit"
            aria-label="open drawer"
            onClick={handleDrawerOpen}
            className={clsx(classes.menuButton, open && classes.menuButtonHidden)}
          >
            <MenuIcon />
          </IconButton>
          <Typography component="h1" variant="h6" color="#6b5b95" noWrap className={classes.title}>
           {location.state.name.userName}
          </Typography>
          <IconButton color="inherit">
            <Badge badgeContent={4} color="secondary">
              <NotificationsIcon />
            </Badge>
          </IconButton>
        </Toolbar>
      </AppBar>
      <Drawer
        variant="permanent"
        classes={{
          paper: clsx(classes.drawerPaper, !open && classes.drawerPaperClose),
        }}
        open={open}
      >
        <div className={classes.toolbarIcon}>
          <Typography component="h1" variant="h5" color="#6b5b95" style={{ fontWeight: 'bold', color: '#6b5b95' }} noWrap className={classes.title}>
            Twilio Chat
          </Typography>
          <IconButton onClick={handleDrawerClose}>
            <ChevronLeftIcon />
          </IconButton>
        </div>
        <Divider />
        <List><UserList /></List>
      </Drawer>
      <main className={classes.content}>
        <div className={classes.appBarSpacer} />

        <Container maxWidth="lg" className={classes.container}>
          <Grid container spacing={3}>

            <Grid item xs={12} >

              <Paper style={{ height: '80vh' }} className={fixedHeightPaper}>
                {message.map((textMessage) => (
                  <List key={textMessage.sid}>
                    <ListItem>
                      <ListItemAvatar>
                        <Avatar style={{ textTransform: 'capitalize' }} alt={textMessage.author} src="logo.jpg" >
                        </Avatar>
                      </ListItemAvatar>
                      <ListItemText primary={textMessage.body} />
                    </ListItem>,
                  </List>
                ))}
                <div style={{ display: 'flex' }}>

                  <TextField
                    id="standard-full-width"

                    style={{ margin: 8 }}
                    placeholder="Message"
                    fullWidth
                    margin="normal"
                    InputLabelProps={{
                      shrink: true,
                    }}
                  />

                  <button><SendIcon onClick={() => sendMessage()} />  </button>
                </div>
              </Paper>
            </Grid>
          </Grid>
          <Box pt={4}>
            <Copyright />
          </Box>
        </Container>
      </main>
    </div>
  );
}