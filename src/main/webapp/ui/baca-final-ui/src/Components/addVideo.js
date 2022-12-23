const handleAddVideo = () => {
    let i = 1;
    let newVids = [...tikTokVideos];
    newVids.push({id: `${i}`, poster: '', videoUri: VIDEOS.video1});
    setTikTokVideos(newVids);
    i++;
}