<template>
   <div class="header-vD9">
  <div class="header-menu-bar--4KM">
    <div class="logo-R9u">
      <img class="logo-sito-web-optimized-KFH" src="./../../assets/img/header/logo.png"/>
      <div class="dieti23-lrr-e2f">
        <span class="dieti23-lrr-e2f-sub-0">Dieti23 </span>
        <span class="dieti23-lrr-e2f-sub-1">LRR</span>
      </div>
    </div>
    <div class="voci-menu-RBq">
      <p class="menu-item-Ncs">Ciao {{utente}}</p>
      <div class="auto-group-f6dq-WMh">
        <p class="menu-item-Sm9">Le tue Aste</p>
        <p class="menu-item-Ax3">Notifiche</p>
      </div>
      <div class="crea-asta-JYT">Crea un Asta</div>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      utente: "guest"
    }
  },

  mounted() {
    axios
      .get('http://localhost:8080/testModel/api/1')
      .then((response) => {
        this.utente = response.data.stringa
      })
  }
}

</script>

<style scoped>

html {
	font-size:50%;
}
* {
	margin: 0;
	padding: 0;
}
ul, li {
	list-style: none;
}
input {
	border: none;
}
body {
  width: 80rem;
}.header-vD9 {
  box-sizing: border-box;
  /*padding: 0rem 15.4rem 0rem 5rem;*/
  width: 100%;
  height: 3.5rem;
  /*background-color: #d42222;*/
}
.header-vD9 .header-menu-bar--4KM {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
}
.header-vD9 .header-menu-bar--4KM .logo-R9u {
  margin: 0.25rem 42rem 0.25rem 0rem;
  box-sizing: border-box;
  padding: 0.008rem 0.65rem 0.007rem 0.013rem;
  height: calc(100% - 0.5rem);
  cursor: pointer;
  overflow: hidden;
  position: relative;
  display: flex;
  align-items: center;
  flex-shrink: 0;
}
.header-vD9 .header-menu-bar--4KM .logo-R9u .logo-sito-web-optimized-KFH {
  margin-right: 1.1613rem;
  width: 2.9755rem;
  height: 2.9848rem;
  position: relative;
  object-fit: contain;
  vertical-align: top;
  flex-shrink: 0;
}
.header-vD9 .header-menu-bar--4KM .logo-R9u .dieti23-lrr-e2f {
  margin-bottom: 0.0012rem;
  display: flex;
  align-items: baseline;
  justify-content: center;
  font-size: 2rem;
  font-weight: 800;
  line-height: 1.2083333969;
  color: #000000;
  font-family: Inter, 'Source Sans Pro';
  white-space: nowrap;
  flex-shrink: 0;
}
.header-vD9 .header-menu-bar--4KM .logo-R9u .dieti23-lrr-e2f .dieti23-lrr-e2f-sub-0 {
}
.header-vD9 .header-menu-bar--4KM .logo-R9u .dieti23-lrr-e2f .dieti23-lrr-e2f-sub-1 {
  font-size: 1rem;
  font-weight: 800;
  line-height: 2.4166667938;
  color: #000000;
  font-family: Inter, 'Source Sans Pro';
}
.header-vD9 .header-menu-bar--4KM .voci-menu-RBq {
  height: 100%;
  display: flex;
  align-items: center;
  flex-shrink: 0;
  font-size: 14px;
}
.header-vD9 .header-menu-bar--4KM .voci-menu-RBq .menu-item-Ncs {
  margin-right: 1rem;
  cursor: pointer;
  font-weight: 600;
  line-height: 1.2083333333;
  color: rgba(0, 0, 0, 0.5);
  font-family: Inter, 'Source Sans Pro';
  white-space: nowrap;
  flex-shrink: 0;
}
.header-vD9 .header-menu-bar--4KM .voci-menu-RBq .menu-item-JFd {
  box-sizing: border-box;
  padding: 1rem 1rem 1rem 1rem;
  height: 100%;
  cursor: pointer;
  display: flex;
  align-items: center;
  flex-shrink: 0;
}
.header-vD9 .header-menu-bar--4KM .voci-menu-RBq .menu-item-JFd .menu-item-qFZ {
  margin-right: 1rem;
  cursor: pointer;
  font-weight: 600;
  line-height: 1.2083333333;
  color: rgba(0, 0, 0, 0.5);
  font-family: Inter, 'Source Sans Pro';
  white-space: nowrap;
  flex-shrink: 0;
}
.header-vD9 .header-menu-bar--4KM .voci-menu-RBq .menu-item-JFd .polygon-1-Mzb {
  width: 1.0392rem;
  height: 0.825rem;
  object-fit: contain;
  vertical-align: top;
  flex-shrink: 0;
}
.header-vD9 .header-menu-bar--4KM .voci-menu-RBq .auto-group-f6dq-WMh {
  box-sizing: border-box;
  padding: 1rem 1rem 1rem 1.5rem;
  height: 100%;
  display: flex;
  align-items: center;
  flex-shrink: 0;
}
.header-vD9 .header-menu-bar--4KM .voci-menu-RBq .auto-group-f6dq-WMh .menu-item-Sm9 {
  margin-right: 2.5rem;
  cursor: pointer;
  font-weight: 600;
  line-height: 1.2083333333;
  color: rgba(0, 0, 0, 0.5);
  font-family: Inter, 'Source Sans Pro';
  white-space: nowrap;
  flex-shrink: 0;
}
.header-vD9 .header-menu-bar--4KM .voci-menu-RBq .auto-group-f6dq-WMh .menu-item-Ax3 {
  cursor: pointer;
  font-weight: 600;
  line-height: 1.2083333333;
  color: rgba(0, 0, 0, 0.5);
  font-family: Inter, 'Source Sans Pro';
  white-space: nowrap;
  flex-shrink: 0;
}
.header-vD9 .header-menu-bar--4KM .voci-menu-RBq .crea-asta-JYT {
  width: 7.5rem;
  height: 80%;
  cursor: pointer;
  font-weight: 500;
  line-height: 1;
  color: #ffffff;
  font-family: Inter, 'Source Sans Pro';
  white-space: nowrap;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #000000;
  border-radius: 0.8rem;
  flex-shrink: 0;
  margin-right: 10rem;
}

</style>