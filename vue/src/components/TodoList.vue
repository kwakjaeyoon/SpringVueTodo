<template>
        <div class="List">
          <div>
            <select v-model="filterOption"  >
              <option value="all"> All </option>
              <option value="todo"> Todo </option>
              <option value="done"> Done </option>
            </select>
          </div>
          <ul>
              <li v-for="todo in filteredTodo" :key="todo.id" :class="{'check' : todo.status==1}" >
                <div>
                  <input type="checkbox" v-model="todo.status" @change="updateTodoStatus(todo,$event)" :disabled="todo.status ==1"/>
                  <span>{{ todo.item }}</span>
                </div>
                <div>
                  <span class="date">{{ todo.date }}</span>
                  <button @click="removeTodo(todo.id)">삭제</button>
                </div>
              </li>
          </ul>
        </div>
</template>

<script>
import axios from 'axios';
import EventBus from '../eventBus';


export default {
  data(){
    return{
        todos:[],
        filterOption: 'all'
    };
  },
  mounted() {
    this.fetchTodo();
    EventBus.on('add-todo', this.fetchTodo);
    EventBus.on('update-todo', this.fetchTodo);
  },
  methods: {
    fetchTodo(){
        axios.get('/todos')
            .then(response =>{
                this.todos = response.data;
            })
            .catch(error =>{
                console.error('Error fetching todos:', error);
            });
    },
    removeTodo(todoId){
        axios.delete(`/todos/${todoId}`)
            .then(response =>{
              if(this.removeCheck()==true){
              EventBus.emit('remove-todo');
              this.todos = this.todos.filter(todo => todo.id != todoId);  
              console.log(response.data);
              } else;
            })
            .catch(error =>{
                console.error('Error remove todos:', error);
            })
    },
    removeCheck() {
      if (confirm("정말 삭제하시겠습니까??") == true){   
          return true;
      }else{   
          return false;
      }
    },
    updateTodoStatus(todo,event) {
      let stat = event.target.checked;
      // eslint-disable-next-line no-unused-vars
      const updatedTodo = {
        id: todo.id,
        item: todo.item,
        date: todo.date,
        status: stat
      };
      axios.put(`/todos/${todo.id}`, updatedTodo)
        .then(response => {
          EventBus.emit('update-todo');
          console.log('Todo status updated:', response.data);
        })
        .catch(error => {
          console.error('Error updating todo status:', error);
        });
    }
  },
  beforeUnmount() {
    EventBus.off('add-todo', this.fetchTodos); // 컴포넌트 언마운트 시 이벤트 해제
    EventBus.off('update-todo', this.fetchTodos); // 컴포넌트 언마운트 시 이벤트 해제
  },
  computed: {
    filteredTodo() {
      if (this.filterOption === "todo") {
          return this.todos.filter(todo => !todo.status);
      } else if (this.filterOption === "done") {
          return this.todos.filter(todo => todo.status);
      } else {
          return this.todos;
      }
    }
  }
}
</script>

<style scoped>
.List{
  margin-top: 50px;
}
ul {
  list-style-type: none;
  padding: 0;
}

li {
  background: #ffffff;
  border: 1px solid #dee2e6;
  border-radius: 5px;
  padding: 10px;
  margin-bottom: 5px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

li.check{
  background-color: #808080;
  text-decoration: line-through;
}

li input[type="checkbox"] {
  margin-right: 10px;
}


button {
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 7px;
  padding: 5px 5px;
  cursor: pointer;
}

.date{
  font-size: 12px;
  margin-right: 10px;
}

.update {
  background-color: #ffc107;
  color: white;
}

.update:hover {
  background-color: #e0a800;
}

.delete {
  background-color: #dc3545;
  color: white;
}

.delete:hover {
  background-color: #c82333;
}
</style>