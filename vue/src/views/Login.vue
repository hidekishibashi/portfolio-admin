<template lang="pug">
  div(class="login")
    p(class="title")
     | Login
    div(class="input-area")
      b-form-input(
        v-model="user"
        type="text"
        placeholder="input user name"
      )
      b-form-input(
        v-model="password"
        type="text"
        placeholder="input password"
      )
    div(class="button-area")
      b-button(
        @click="login()"
        variant="outline-primary"
      ) Login
      b-button(
        @click="reset()"
      ) Reset
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      user: "",
      password: ""
    };
  },

  methods: {
    login() {
      // TODO: login execute and error handling
      this.$router.push({ path: "/skill" });
    },

    reset() {
      this.user = "";
      this.password = "";
    }
  }
};
</script>

<style lang="scss" scoped>
.login {
  width: 50vw;
  height: 40vh;
  position: relative;
  margin: auto;
  margin-top: 30vh;
  .title {
    font-size: 30px;
  }
  .input-area {
    padding: 3vh 10vw 3vh 10vw;
    input[type="text"]:nth-child(n + 2) {
      margin-top: 2vh;
    }
  }
  .button-area {
    padding: 2vh 10vw 3vh 10vw;
    button:nth-child(n + 2) {
      margin-left: 3vw;
    }
  }
}
</style>
