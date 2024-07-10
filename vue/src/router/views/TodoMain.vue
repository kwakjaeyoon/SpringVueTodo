<template>
    <TodoHeader/>
    <TodoCount/>
    <TodoInput/>
    <TodoList/>
    <TodoFooter/>
  </template>
  
  <script>
  import TodoHeader from '../../components/TodoHeader.vue'
  import TodoCount from '../../components/TodoCount.vue'
  import TodoInput from '../../components/TodoInput.vue'
  import TodoList from '../../components/TodoList.vue'
  import TodoFooter from '../../components/TodoFooter.vue'
  
  export default {
    name: 'App',
    components: {
      TodoHeader,
      TodoCount,
      TodoInput,
      TodoList,
      TodoFooter
    }
  }
  </script>
  
  <style>
  </style>