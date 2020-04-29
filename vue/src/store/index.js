import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

const config = {
  headers: {
    "Access-Control-Allow-Origin": "*"
  }
};

export default new Vuex.Store({
  state: {
    skillCategories: [],
    skills: []
  },
  mutations: {
    setSkillCategories(state, payload) {
      state.skillCategories = payload.skillCategories;
    },
    setSkills(state, payload) {
      state.skills = payload.skills;
    }
  },
  actions: {
    fetchSkillInfos({ commit }) {
      const url = "/api/skills";
      axios.get(url + "/categories", config).then(res => {
        const skillCategories = res.data;
        commit("setSkillCategories", { skillCategories });
      });
      axios.get(url, config).then(res => {
        const skills = res.data;
        commit("setSkills", { skills });
      });
    }
  },
  modules: {}
});
