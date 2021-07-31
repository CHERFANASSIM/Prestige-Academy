package com.isika.prestigeacademy.repositories;

import com.isika.prestigeacademy.model.entities.NiveauxPriorite;
import com.isika.prestigeacademy.model.entities.ProgressionToDo;
import com.isika.prestigeacademy.model.entities.Todolist;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;


@Stateless
public class ToDoListRepository {
	
	private static final Logger LOGGER = Logger.getLogger(ToDoListRepository.class.getSimpleName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private Event<Todolist> todolistEventSource;
	
	public Long addTodo(Todolist todo) {
		this.entityManager.persist(todo);
		this.entityManager.flush();
		todolistEventSource.fire(todo);
		LOGGER.info("Persisted todo : " + todo);
		return todo.getTodolistID();
	}
	public List<Todolist> findAll() {
		return this.entityManager.createNamedQuery("Todolist.findAll",Todolist.class).getResultList();
	}
	public List<NiveauxPriorite> findAllNiveauPriorite() {
		return this.entityManager.createNamedQuery("NiveauxPriorite.findAll", NiveauxPriorite.class).getResultList();
	}
	
	public List<ProgressionToDo> findAllProgressionToDO() {
		return this.entityManager.createNamedQuery("ProgressionToDo.findAll", ProgressionToDo.class).getResultList();
	}
	
	public boolean remove(Todolist todo) {
		this.entityManager.remove(todo);
		return true;
	}
	public Todolist update(Todolist SelectedTodo) {
		return this.entityManager.merge(SelectedTodo);
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public Event<Todolist> getTodolistEventSource() {
		return todolistEventSource;
	}
	public void setTodolistEventSource(Event<Todolist> todolistEventSource) {
		this.todolistEventSource = todolistEventSource;
	}

	public Todolist findById(Long long1) {
		
		return this.entityManager.find(Todolist.class, long1);
	}
	
	public Todolist passerEnCours(Todolist tacheSelected) {
		return this.entityManager.merge(tacheSelected);
	}
	public List<Todolist> rechercheTaches(long todolistID) {
		TypedQuery<Todolist> query = this.entityManager.createNamedQuery("Todolist.findEnCours", Todolist.class);
		query.setParameter("progression", todolistID);
		return query.getResultList();
	}
	
	





	
	

}
