import Vue from "vue";
import VueRouter from "vue-router";
import VueSession from "vue-session";

Vue.use(VueRouter);
Vue.use(VueSession);

const router = new VueRouter({
  mode: "history",
  scrollBehavior() {
    return { x: 0, y: 0 };
  },
  routes: [
    {
      path: "/",
      name: "defaultLayout",
      component: () => import("@/layouts/DefaultLayout.vue"),
      children: [
        {
          path: "/",
          name: "home",
          component: () => import("@/pages/mainpage/Main.vue"),
        },
        {
          path: "/best",
          name: "bestgood",
          component: () => import("@/pages/best/Best.vue"),
        },
        {
          path: "/new",
          name: "newgood",
          component: () => import("@/pages/newproductpage/New.vue"),
        },
        {
          path: "/sale",
          name: "sale",
          component: () => import("@/pages/sale/Sale.vue"),
        },
        {
          path: "/event",
          name: "event",
          component: () => import("@/pages/event/Event.vue"),
        },
        {
          path: "/brand/:brandNum",
          name: "brand",
          component: () => import("@/pages/brand/Brand.vue"),
        },
        {
          path: "/category/:category",
          name: "category",
          component: () => import("@/pages/category/Category.vue"),
        },
        {
          path: "/detail/:productNum",
          name: "detail",
          component: () => import("@/pages/detail/Product.vue"),
        },
        {
          path: "/delivery",
          name: "delivery",
          component: () => import("@/pages/subpage/Delivery.vue"),
        },
        {
          path: "/order/sheet",
          name: "orderSheet",
          component: () => import("@/pages/order/Sheet.vue"),
          beforeEnter: function (to, from, next) {
            if (from.path === "/cart") {
              next();
            } else {
              alert("잘못된 접근 방식입니다.");
              next({ path: "/" });
            }
          },
        },
        {
          path: "/order/success",
          name: "orderSuccess",
          component: () => import("@/pages/order/Success.vue"),
          beforeEnter: function (to, from, next) {
            if (from.path === "/order/sheet") {
              next();
            } else {
              alert("잘못된 접근 방식입니다.");
              next({ path: "/" });
            }
          },
        },
        {
          path: "/mypage",
          name: "mypage",
          component: () => import("@/pages/mypage/Mypage.vue"),
          beforeEnter: function (to, from, next) {
            const isLoggedIn = Vue.prototype.$session.get("secretKey");

            if (isLoggedIn === undefined) {
              alert("로그인 후 이용가능 합니다.");
              next({ path: "/login" });
            } else {
              next();
            }
          },
        },
        {
          path: "/cart",
          name: "cart",
          component: () => import("@/pages/cart/Cart.vue"),
          beforeEnter: function (to, from, next) {
            const isLoggedIn = Vue.prototype.$session.get("secretKey");

            if (isLoggedIn === undefined) {
              alert("로그인 후 이용가능 합니다.");
              next({ path: "/login" });
            } else {
              next();
            }
          },
        },
        {
          path: "/login",
          name: "login",
          component: () => import("@/pages/login/Login.vue"),
        },
        {
          path: "/join",
          name: "join",
          component: () => import("@/pages/join/Join.vue"),
        },
        {
          path: "/findId",
          name: "findId",
          component: () => import("@/pages/login/FindId.vue"),
        },
        {
          path: "/findPw",
          name: "findPw",
          component: () => import("@/pages/login/FindPw.vue"),
        },
        {
          path: "/search",
          name: "search",
          component: () => import("@/pages/search/Search.vue"),
          props: true,
        },
        {
          path: "/cs",
          name: "customerservice",
          component: () => import("@/pages/cs/CustomerService.vue"),
          props: true,
        },
        {
          path: "/notice/detail/:num",
          name: "noticeDetail",
          component: () => import("@/components/cs/NoticeDetail.vue"),
          props: true,
        },
        {
          path: "/inquiry",
          name: "onetooneform",
          component: () => import("@/components/cs/OneToOneForm.vue"),
          props: true,
        },
        {
          path: "/inquiry/update",
          name: "onetooneupdateform",
          component: () => import("@/components/cs/EditOneToOneForm.vue"),
          props: true,
        },
        {
          path: "/address",
          name: "updateaddress",
          component: () => import("@/pages/address/AddressUpdate.vue"),
          beforeEnter: function (to, from, next) {
            const isLoggedIn = Vue.prototype.$session.get("secretKey");

            if (isLoggedIn === undefined) {
              alert("로그인 후 이용가능 합니다.");
              next({ path: "/login" });
            } else {
              next();
            }
          },
          props: true,
        },
      ],
    },
    {
      path: "/",
      name: "emptyLayout",
      component: () => import("@/layouts/EmptyLayout.vue"),
      children: [],
    },
  ],
});

export default router;
