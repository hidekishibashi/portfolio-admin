import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../views/Login.vue";
import Skill from "../views/Skill.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login
  },
  {
    path: "/skill",
    name: "Skill",
    component: Skill
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
