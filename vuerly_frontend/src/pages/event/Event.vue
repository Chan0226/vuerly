<template>
    <div class="brand-page">
      <h1>특가/혜택</h1>
      <div class="brand-body">
        <div class="event-side">
          <div class="countdown-wrap">
            <countdown :end="endDate"></countdown>
          </div>
          <side-menu v-on:pass="deliverCategory"></side-menu>
        </div>
        <event-content
          v-bind:propsdata="num"
          v-on:propsevent="propsEvent"
        ></event-content>
      </div>
    </div>
  </template>
  
  <script>
  import SideMenu from "../../components/SideMenu.vue";
  import EventContent from "../../components/event/EventContent.vue";
  import Countdown from '../../components/event/Countdown.vue';
  
  export default {
    data: function () {
      return {
        str: "Headers",
        // props로 내릴 data
        num: "props",
        endDate: '2023-02-28',
      };
    },
    methods: {
      propsEvent() {
        console.log("propsEvent");
      },
      deliverCategory(value) {
        this.num = value;
      },
    },
    components: {
      "side-menu": SideMenu,
      "event-content": EventContent,
      "countdown": Countdown
    },
  };
  </script>
  
  <style>
  .brand-page {
    display: flex;
    flex-direction: column;
    padding: 50px 0;
    width: 1050px;
    margin: auto;
  }
  div.brand-page > h1 {
    text-align: center;
    margin-bottom: 50px;
  }
  .brand-body {
    display: flex;
  }

  .brand-desc {
    width: 100%;
    padding-bottom: 50px;
  }

  .event-side {
    display: flex;
    flex-direction: column;
  }

  .countdown-wrap {
    text-align: center;
    font-size: 28px;
    margin-bottom: 20px;
    color: #5f0080;
    font-weight: bold;
  }
  </style>