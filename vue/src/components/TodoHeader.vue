<template>
  <div class="header">
    <h2 class="logo">My To do </h2>
    <p class="today">{{timeStamp}}</p>
  </div>
</template>

<script>
import { updateTimeStamp }  from '../timeStamp.js';

export default {
  name: 'TodoHeader',
  data(){
    return{
      timeStamp:"",
      cnt:""
    };
  },
  created(){
    this.timeStamp = updateTimeStamp();
  },
  methods:{
  }
}
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo {
  margin: 0;
  color: #0d3ee1;
  font-size: 20px;
}

.today{
  margin: 0;
  color: #343a40;
  font-size: 16px;
}

</style>