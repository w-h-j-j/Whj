package com.mingrisoft.whj.db.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository repository;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        this.repository=new StudentRepository(application);
    }

    /**插入数据*/
    public void insertData(Student student){
        repository.insertData(student);
    }

    /**删除数据*/
    public void deleteData(Student student){
        repository.deleteData(student);
    }

    /**通过名字删除数据*/
    public void deleteData(String name){
        repository.deleteData(name);
    }

    /**删除所有数据*/
    public void deleteData(){
        repository.deleteData();
    }

    /**数据库的变化*/
    public LiveData<List<Student>> queryAllData(){
        return repository.getAllData();
    }

    public void updateData(Student student){
        repository.updateData(student);
    }

    public int studentExitByName(String name){
        return repository.studentExitByName(name);
    }

    public List<Student> getStudentByName(String name){
        return repository.getStudentByName(name);
    }

    public List<Student> getStudentByAge(String age){
        return repository.getStudentByAge(age);
    }

    public Student queryDataById(int id){
        if (repository.studentExitById(id)==1){
            return repository.queryDataById(id);
        }else {
            System.out.println("该Id不存在");
            return null;
        }

    }

    public List<Student> getStudentThanId(int id){
        return repository.getStudentThanId(id);
    }

    public List<Student> getStudentBetweenId(int minId,int maxId){
        return repository.getStudentBetweenId(minId, maxId);
    }
}
