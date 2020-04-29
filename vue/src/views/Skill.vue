<template lang="pug">
  div(class="skill")
    p(class="title")
     | Skill
    b-nav(pills)
      b-nav-item(
        v-for="(category, index) in skillCategories"
        :key="category.id"
        :active="isActiveTab(index)"
        v-on:click="tabIndex = index"
      ) {{ category.name }}
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  name: "Skill",
  data() {
    return {
      tabIndex: 0
    };
  },

  computed: {
    ...mapState(["skillCategories"]),

    isActiveTab: function() {
      return function(index) {
        return this.tabIndex == index;
      };
    }
  },

  created() {
    this.fetchSkillInfos();
  },

  methods: {
    ...mapActions(["fetchSkillInfos"])
  }
};
</script>

<style lang="scss" scoped>
.skill {
  width: 50vw;
  height: 80vh;
  position: relative;
  margin: auto;
  margin-top: 10vh;
  margin-bottom: 10vh;
  .title {
    font-size: 30px;
  }
}
</style>
