(function(configuration){//{parseLrc,addLrc}
  var audio = new Audio(), //
  //add(obj) ==> return index
  //  on add(index,obj) ==> return src,lrc
  //{src,lrc}
  //
  //remove(index) ==> {src}.remove(index)
  //  on remove(index)
  //
  //clear() ==> {src}.clear()
  //  on clear() / each remove(index)
  music = [], //
  //playMusic(index) ==> play()
  //play() ==> on play
  //pause() ==> on pause
  playIndex = -1, //
  currentMusic, //
  //parse ==> {ar,ti,lyrics}
  //each lyrics ==>
  //  lyrics(time,content) ==> return a function to call at show time
  //  {time,callback}
  lrcs = [], //
  lrcIndex, //
  mode, //random single circulation order
  played, //
  volume, //100%
  events = {
    add : [],
    remove : [],
    clear : [],
    play : [],
    pause : []
  }, //add remove clear play pause
  trigger = function(type){
    events[type].forEach(function(e){e();});
  }, //
  config = {
    add : configuration.add || function(index,obj){return obj;},
    remove : configuration.remove || function(){},
    createLrc : configuration.createLrc || function(){},
    parseLrc : configuration.parseLrc || function(lrc){
      if(!lrc) return [];
      var lyrics = lrc.match(/\[\d{2}:\d{2}\.\d{2}\].*[\n\r]/g);
      if(!lyrics) return [];
      return lyrics.map(function(lyric){
        var temp = lyric.match(/^\[(\d{2}):(\d{2})\.(\d{2})\](.*)[\n\r]$/);
        return {
          time : parseInt(temp[1])*60 + parseInt(temp[2]) + parseInt(temp[3])/100,
          content : temp[4]
        };
      });
    }
  }
  ;

  window.Music = {
    play : function(time){
      this.playMusic(playIndex > 0 ? playIndex : 0, time);
    },
    pause : function(){
      audio.pause();
      trigger("pause");
    },
    toggle : function(){
      if(audio.paused) this.play();
      else this.pause();
    },
    playMusic : function(index, time){
      if(music.size() === 0) return;
      if(Object.prototype.toString.call(index) !== "[object Number]")
        index = 0;
      if(music.size() < index || index < 0)
        index = 0;

      clearInterval(played);
      if(playIndex !== index){
        playIndex = index;
        audio.src = music[index].src;
        (lrcs = config.parseLrc(music[index].lrc)).forEach(function(lyric){
          lyric.update = config.createLrc(lyric.time,lyric.content);
        });
        lrcIndex = 0;
      }

      if(Object.prototype.toString.call(time) === "[object Number]"){
        audio.currentTime = time;
        while(lrcIndex < lrcs.length - 1 && time > lrcs[lrcIndex]){
          lrcIndex++;
        }
        while(lrcIndex > 0 && time < lrcs[lrcIndex]){
          lrcIndex--;
        }
        lrcs[lrcIndex].update();
      }

      played = setInterval(function(){
        var time = audio.currentTime;
        if(lrcIndex > lrcs.length - 1 || time < lrcs[lrcIndex].time || time > lrcs[lrcs.length - 1])
          return;
        lrcs[lrcIndex].update();
        lrcIndex++;
      },100);
      audio.play();
      trigger("play");
    },
    setMusic : function(index){
      if(music.size() === 0) return ;
      if(Object.prototype.toString.call(index) !== "[object Number]")
        index = 0;
      if(music.size() < index || index < 0)
        index = 0;

      if(Object.prototype.toString.call(time) === "[object Number]"){
        audio.currentTime = time;
        while(lrcIndex < lrcs.length - 1 && time > lrcs[lrcIndex]){
          lrcIndex++;
        }
        while(lrcIndex > 0 && time < lrcs[lrcIndex]){
          lrcIndex--;
        }
        lrcs[lrcIndex].update();
      }else{
        time = audio.currentTime;
      }

      if(playIndex !== index){
        playIndex = index;
        audio.src = music[index].src;
        (lrcs = config.parseLrc(music[index].lrc)).forEach(function(lyric){
          lyric.update = config.createLrc(lyric.time,lyric.content);
        });
        lrcIndex = 0;
      }



      played = setInterval(function(){
        var time = audio.currentTime;
        if(lrcIndex > lrcs.length - 1 || time < lrcs[lrcIndex].time || time > lrcs[lrcs.length - 1])
          return;
        lrcs[lrcIndex].update();
        lrcIndex++;
      },100);
      audio.play();
      trigger("play");
    },
    add : function(obj){
      music.push(config.add(music.length+1,obj));
    },
    remove : function(index){
      if(Object.prototype.toString.call(index) !== "[object Number]")
        return;
      if(index < 0 || index > music.length - 1)
        return;
      this.pause();
      if(playIndex < index)
      else if(playIndex === index){
        playIndex = 0;
      }
      else playIndex--;
      music.filter(function(e,i){return i !== index;});
      config.remove(index);
      this.play();
    },
    clear : function(){
      playIndex = -1;

    }

    setMode
    volume

    on play/pause/ended/error
  };
})()
