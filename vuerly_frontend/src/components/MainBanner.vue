<template>
  <div>
    <v-carousel hide-delimiters cycle interval="10000" height="400px">
      <v-carousel-item v-for="(item, i) in items" :key="i" :src="item.src">
        <div class="current-num" style="margin-bottom: 5px">
          {{ i + 1 }}/{{ items.length }}
        </div>
      </v-carousel-item>
    </v-carousel>
    <div></div>
  </div>
</template>
<!--         reverse-transition="fade-transition" -->
<script>
export default {
  data() {
    return {
      items: [
        {
          //   src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
          src: require(`@/static/banner1.jpg`),
        },
        {
          src: require(`@/static/banner3.jpg`),
        },
        {
          src: require(`@/static/banner4.jpeg`),
        },
        {
          src: require(`@/static/banner2.jpg`),
        },
        // {
        //   src: require(`@/static/banner5.jpg`),
        // },
        {
          src: require(`@/static/banner6.jpg`),
        },
        {
          src: require(`@/static/banner7.jpg`),
        },
      ],
    };
  },
};
</script>

<style>
.v-window__next {
  margin-right: 13%;
}
.v-window__prev {
  margin-left: 13%;
}
.current-num {
  position: absolute;
  right: 20%;
  bottom: 0;
  color: white;
}
</style>
