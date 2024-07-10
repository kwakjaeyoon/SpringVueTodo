<template>
  <div class="menu">
    <div class="input-group">
      <input type="text" class="item" v-model="newTodo.item" placeholder="Input your to do list">
    </div>
    <button class="submit" @click="addTodo">추가</button>
  </div>
</template>

<script>
import axios from 'axios';
import EventBus from '../eventBus';
import { updateTimeStamp }  from '../timeStamp.js';

export default {
  name: 'TodoInput',
  data() {
    return {
      newTodo: {
        item: '',
        date: updateTimeStamp(),
        status:false
      }
    };
  },
  methods: {
    addTodo() {
      if (this.newTodo.item.trim() === '') {
        alert('할 일을 입력해주세요.');
        return;
      }
      axios.post('/todos', this.newTodo)
        .then(response => {
          // Do something here after adding todo if needed
          EventBus.emit('add-todo');
          console.log('Todo added:', response.data);
          // Reset the form
          this.newTodo = { item: '', date: updateTimeStamp(), status:false };
        })
        .catch(error => {
          if(error.response && error.response.data.includes('Item already exists')){
            alert("이미 존재하는 일입니다.");
          }else{
            console.error('Error adding todo:', error);
          }
          
        });
    }
  }
}
</script>

<style scoped>
.menu {
  background: white;
  padding: 10px;
  margin-bottom: 20px;
}

.input-group {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.date {
  width: 20%;
  margin-right: 10px;
  padding: 5px;
  border: 1px solid #ced4da;
  border-radius: 5px;
}

.item {
  flex: 1;
  height: 30px;
  padding: 5px;
  border: 1px solid #ced4da;
  border-radius: 15px;
}

.submit {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 5px;
  cursor: pointer;
  width: 100%;
}

.submit:hover {
  background-color: #0056b3;
}
</style>