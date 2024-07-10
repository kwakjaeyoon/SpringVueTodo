<template>
    <div class="content">
      <h1> 해야 할 일 : {{cnt}} 개 </h1>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import EventBus from '../eventBus';
  
  export default {
    name: 'TodoHeader',
    data(){
      return{
        cnt:""
      };
    },
    created(){
      this.fetchTodo();
      EventBus.on('add-todo', this.fetchTodo);
      EventBus.on('remove-todo', this.fetchTodo);
      EventBus.on('update-todo', this.fetchTodo);
    },
    methods:{
      fetchTodo(){
          axios.get('/todos')
              .then(response =>{
                const result = response.data.filter(todo => todo.status ==false)
                this.cnt = result.length;
              })
              .catch(error =>{
                  console.error('Error fetching todos:', error);
              });
      }
    }
  }
  </script>
  
  <style scoped>

  .content{
    margin-top: 100px;
  }
  
  </style>