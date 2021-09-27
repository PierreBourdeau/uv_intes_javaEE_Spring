const { request } = require("express");
const express = require("express");
const app = express();

app.use(express.urlencoded({ extended: false }));
const axios = require('axios').default;
app.set('view engine', 'ejs');
const backurl = 'http://localhost:8080';
app.use(express.json());


app.use(express.static('views'));
app.use(express.static('assets'));
app.use(express.static('style'));

//ROUTES 
app.get('/', async function(req,res,next) {
  axios.get(backurl+'/songs').then(function (resp) {
    res.render('index', {musics : resp.data });
  });
});

app.get('/search/:guid', function (req,res, next) {
  let id = req.params.guid;
  axios.get(backurl+'/songs/'+id).then(function (resp) {
    res.render('searchResult', {music: resp.data});
  }).catch(err => { res.render('searchResult', {music : 'empty' });});

});

app.post('/create', function (req, res, next) {
  axios.post(backurl+'/songs', req.body).then(function (resp) {
    axios.get(backurl+'/songs').then(function (response) {
      res.render('table', {musics: response.data});
    });
  }).catch(err => err);
});

app.get('/delete/:guid', function (req, res, next) {
  let id = req.params.guid;
  axios.delete(backurl+'/songs/'+id).then(function (resp) {
    axios.get(backurl+'/songs').then(function (response) {
      res.render('table', {musics: response.data});
    });
  });
});

app.get('/artistslist',function(req,res,next) {
  axios.get(backurl+'/songs').then(async function(resp) {
    let songList = resp.data;
    var result = {};
    let artists = songList.map(a => a.artist);
    artists = artists.filter((v,i) => artists.indexOf(v) === i);
    let requests =[];
    for (let count = 0; count < artists.length; count++) {
      let datas = await axios.get(backurl+'/artists/'+artists[count]);
      requests.push({artist: artists[count], songs: datas.data});
    };
    res.render('artistTable', {artists: requests});
  });
});

app.put('/edit/:guid', function (req,res, next) {
  let id = req.params.guid;
  axios.put(backurl+'/songs/'+id, req.body).then(function (resp) {
    axios.get(backurl+'/songs').then(function (response) {
      res.render('table', {musics: response.data});
    });
  }).catch(err => err);

});

app.listen(process.env.PORT || "4200", () => {
  console.log("Le serveur est à l’écoute sur le port 4200");
});
