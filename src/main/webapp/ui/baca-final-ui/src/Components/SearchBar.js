import React, { Component, useEffect, useState } from 'react';
import { IconButton, TextField } from '@mui/material';
import MuiIconButton from '@mui/material/IconButton';
import Divider from '@mui/material/Divider';
import VideoGallery from './VideoGallery';
import TT_VIDEOS from '../videos/ttvids';
import YT_VIDEOS from '../videos/ytvids';
import IG_VIDEOS from '../videos/igvids';
import TT_SCENES from '../videos/ttscenes';
import YT_SCENES from '../videos/ytscenes';
import IG_SCENES from '../videos/igscenes';
import { render } from '@testing-library/react';
import { fontSize } from '@mui/system';
import { ButtonBase } from '@mui/material';
import { Sync } from '@mui/icons-material';
import { FaTiktok } from "react-icons/fa";
import { FaInstagram } from 'react-icons/fa';
import { FaYoutube } from 'react-icons/fa';
import { TailSpin } from 'react-loading-icons';
import PROGRESS_MESSAGE from '../videos/progress';

/*
    The SearchBar component contains the search bar, loading message, and all video galleries.
    In a later revision, the contents of this component can probably just be moved
    to the app.js component, since it contains all children.

*/


function SearchBar() {
    const [searchText, setSearchText] = useState("");
    const [searchTerm, setSearchTerm] = useState("");
    const [tikTokVideos, setTikTokVideos] = useState({});
    const [tikTokScenes, setTikTokScenes] = useState([]);
    const [youTubeVideos, setYouTubeVideos] = useState([]);
    const [youTubeScenes, setYouTubeScenes] = useState([]);
    const [instagramVideos, setInstagramVideos] = useState([]);
    const [instagramScenes, setInstagramScenes] = useState([]);
    const [showGallery, setShowGallery] = useState(false);
    const [showSceneButton, setShowSceneButton] = useState(false);
    const [progress, setProgress] = useState("");
    const [loading, setLoading] = useState("Downloading TikTok Videos...");


    const handleSubmit = (e) =>{
        e.preventDefault();
        console.log({searchTerm});  
        fetch(`http://localhost:8080/search?keyword=${searchTerm}`);
        setTimeout(()=>{setLoading(true)}, 1000);
    };

    useEffect(() => {
        let newTTVids = [];
        let newYTVids = [];
        let newIGVids = [];

        for(let i = 0; i < Object.values(TT_VIDEOS).length; i++) {
            newTTVids[i]=({id: `${i+1}`, poster: '', videoUri: Object.values(TT_VIDEOS)[i]});
        }

        for(let i = 0; i < Object.values(IG_VIDEOS).length; i++) {
            newIGVids.push({id: `${i+1}`, poster: '', videoUri: Object.values(IG_VIDEOS)[i]});
        }
        for(let i = 0; i < Object.values(YT_VIDEOS).length; i++) {
            newYTVids.push({id: `${i+1}`, poster: '', videoUri: Object.values(YT_VIDEOS)[i]});
        }
        setTikTokVideos(newTTVids);
        setInstagramVideos(newIGVids);
        setYouTubeVideos(newYTVids);

        setTikTokScenes(TT_SCENES);
        setInstagramScenes(IG_SCENES);
        setYouTubeScenes(YT_SCENES);
        setProgress(PROGRESS_MESSAGE);
        setLoading(PROGRESS_MESSAGE);

    })

    const handleRefresh = () => {
        let newTTVids = [];
        let newYTVids = [];
        let newIGVids = [];
        
        for(let i = 0; i < Object.keys(TT_VIDEOS).length; i++) {
            newTTVids[i]=({id: `${i+1}`, poster: '', videoUri: Object.values(TT_VIDEOS)[i]});
        }

        for(let i = 0; i < Object.keys(YT_VIDEOS).length; i++) {
            newYTVids.push( {id: `${i+1}`, poster: '', videoUri: Object.values(YT_VIDEOS)[i]} );
        }

        for(let i = 0; i < Object.keys(IG_VIDEOS).length; i++) {
            newIGVids.push( {id: `${i+1}`, poster: '', videoUri: Object.values(IG_VIDEOS)[i]} );
        }

        setShowGallery(true);
        setTikTokVideos(newTTVids);
        setYouTubeVideos(newYTVids);
        setInstagramVideos(newIGVids);
        setTikTokScenes(TT_SCENES);
        setYouTubeScenes(YT_SCENES);
        setInstagramScenes(IG_SCENES);
    }

    return ( 
        <div>
            <div className="container" style={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                padding: '1em',
            }}>
                <div className="col-md-4" style={{justifyContent:'center', textAlign: 'center'}}>
                    {loading && <div style={{}}>
                        <h6 style={{color:'lightgray', paddingLeft: '0em'}}><TailSpin stroke="lightgray" style={{paddingRight: '.75em' }}/>{progress}</h6>
                    </div> }
                </div>
                
                <div className="col-md-4" >
                    <form onSubmit={(e) => {handleSubmit(e)}}>
                        <TextField 
                            id="outlined-basic" 
                            label="Search" 
                            variant="outlined" 
                            value={searchTerm}
                            fullWidth={true}
                            onChange={(e) => {
                                setSearchTerm(e.target.value);
                            }}
                            />
                    </form> 
                </div>
                
                <div className="col-md-4">
                    <IconButton  color="primary" onClick={handleRefresh} style={{justifyContent: 'left', alignItems: 'left'}}>
                        <Sync fontSize="large"/>
                    </IconButton>
                </div> 
                {/* <br></br> */}
                            
            </div>


            <Divider variant='middle' />

                
               {showGallery && <div id="gallery-container">   
                    <div>
                        {tikTokVideos.length > 0 && 
                            <VideoGallery 
                                data={tikTokVideos} 
                                scenes={tikTokScenes} 
                                // data={tikTokVidsAndScenes}
                                className="gallery" 
                                id="tikTok" 
                                name='TikTok' 
                                icon={<FaTiktok/>} /> }
                    </div>
                    <div>
                        {instagramVideos.length > 0 && 
                            <VideoGallery 
                                data={instagramVideos} 
                                scenes={IG_SCENES} 
                                className="gallery-yt" 
                                id="instagram" 
                                name='Instagram' 
                                icon={<FaInstagram/>}/> }
                    </div>
                    <div>
                        {youTubeVideos.length > 0 && 
                            <VideoGallery 
                                data={youTubeVideos} 
                                scenes={YT_SCENES} 
                                setShowSceneButton={false} 
                                className="gallery-yt" 
                                id="youtube" 
                                name='YouTube' 
                                icon={<FaYoutube/>}/> }
                    </div>
                </div> }         
        </div>
     );
}

export default SearchBar;