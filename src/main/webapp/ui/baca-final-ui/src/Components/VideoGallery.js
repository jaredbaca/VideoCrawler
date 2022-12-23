import React, { useState, Component, createRef, useEffect } from 'react';
import { Button } from '@mui/material';
import { CloseButton } from 'react-bootstrap';
import VideoCarousel from './VideoCarousel';
import { FaTiktok } from "react-icons/fa";
import { FaInstagram } from 'react-icons/fa';
import { FaYoutube } from 'react-icons/fa';
import { Divider } from '@mui/material';


//video player
import {DefaultPlayer as Video} from 'react-html5video';
import 'react-html5video/dist/styles.css';

//custom css
import './react-video-gallery.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';


const VideoGallery = (props) =>{
    const [model, setModel] = useState(false);
    const [showCarousel, setShowCarousel] = useState(false);
    const [showSceneButton, setShowSceneButton] = useState(false);

    useEffect(()=>{
        props = {props}
    })

    return ( 
        <div>
             <p></p>
             <p></p>
             <p></p>
            <h1 style={{textAlign:'center'}}> {props.icon} {props.name} </h1>
            <p></p>
             <p></p>
             <p></p>
            <div className="gallery">
                
                {Object.values(props.data).map((item, index)=>{
                    let divRef = createRef(null);
                    const openModel = () =>{
                        divRef.current.classList.remove('video');
                        divRef.current.classList.add('model');
                        setModel(true);
                    }
                    const closeModel = () =>{
                        divRef.current.classList.add('video');
                        divRef.current.classList.remove('model');
                        setModel(false);
                        setShowCarousel(false);
                    }
                    const handleSceneBreakdown = () => {
                        divRef.current.classList.remove('video');
                        divRef.current.classList.add('model');
                        setModel(true);
                        setShowCarousel(!showCarousel);
                    }

                    return(
                        <div ref={divRef} className="video" key={item.videoUri}>
                            {model && <CloseButton className="model-close-btn" onClick={()=>closeModel()}>X</CloseButton>}

                            <div className="video-container">
                                {!showCarousel && <Video 
                                    id={item.id}
                                    style={{width: '100%'}}
                                    autoPlay={model}
                                    controls={['PlayPause', 'Seek','Time', 'Volume','Fullscreen']}
                                    poster={item.poster}
                                    >
                                    <source src={item.videoUri} type='video/webm' />
                                </Video> 
                                }  
                                {!showCarousel && 
                                <div style={{textAlign:'center'}}>
                                    {props.scenes[index]!=null && <Button variant="contained" color="secondary" onClick={handleSceneBreakdown}>Scenes</Button>}
                                </div>
                                }
                                {showCarousel && <VideoCarousel 
                                scenes={props.scenes[index]}
                                />} 
                            </div>
                        </div>
                    )
                })}


            </div>
        </div>
     );
}

export default VideoGallery;