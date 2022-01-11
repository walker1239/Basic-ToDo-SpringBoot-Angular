import { Component, OnInit } from '@angular/core';
import {todomodel} from "./todomodel";
import { TodoService } from './todo.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'todolist';
  edited = false;
  mytodo = new todomodel(0,'',false);
  mytodolist: todomodel[] = [];
  newmytodo=new todomodel(0,'',false);;
  constructor (private dataservice: TodoService) {
  }

  onSubmit() {      
      this.saveTodo(this.mytodo);
      this.mytodo = new todomodel(0,'',false);
    }

  saveTodo(mytodo: todomodel){
    if (!this.edited) {
      if (this.newmytodo.description=='') return;
        this.dataservice.createTodo(this.newmytodo).subscribe(data=> {
          this.displayTodoList();
      });
    }
    else {
      this.edited=false;
      console.log('edited',mytodo);
      this.dataservice.updateTodo(this.mytodo.id,this.mytodo).subscribe(data =>
        {     
          this.displayTodoList();
        }       
        );
    }    
  }

  ngOnInit(){
    this.displayTodoList();
  }

  displayTodoList() {
    this.dataservice.getTodoList().subscribe(data =>
      {
        this.mytodolist = data.sort((a,b) => {
          if (a.id>b.id) return -1;
          if (a.id<b.id) return 1;
          else return 0;
        });
        console.log('display', this.mytodolist);
      });
  }

  Delete(mytodo: todomodel) { 

    if(confirm("Eliminar Item: "+mytodo.description)) {
      console.log('delete', mytodo.id);    
      this.dataservice.deleteTodo(mytodo.id).subscribe(data =>{
        this.displayTodoList();
      });
    }
  }

  Edit(eid: number) { 
    console.log('editing',eid);
    this.mytodo = this.mytodolist.filter(x=>x.id ==eid)[0];
    this.edited = true;   
  }

  FinishTodo(eid: number) {  
    const mytodofinished = this.mytodolist.filter(x=>x.id ==eid )[0];
    mytodofinished.state =  !mytodofinished.state ;
    this.dataservice.updateTodo(eid,mytodofinished).subscribe(data =>{     
        this.displayTodoList();
      });
  }

}
