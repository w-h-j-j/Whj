package com.mingrisoft.whj.db.room;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {

    private StudentDao studentDao;

    public StudentRepository(Context context){
        StudentDatabase instance = StudentDatabase.getInstance(context);
        this.studentDao= instance.studentDao();
    }

    /**插入数据*/
    public void insertData(Student student){
        new Thread(()->{
            studentDao.insertStudent(student);
        }).start();
    }

    /**删除数据*/
    public void deleteData(Student student){
        new Thread(()->{
            studentDao.insertStudent(student);
        }).start();
    }

    /**通过名字删除数据*/
    public void deleteData(String name){
        new Thread(()->{
            studentDao.deleteStudent(name);
        }).start();
    }

    /**删除表上的所有学生*/
    public void deleteData(){
        new Thread(()->{
            studentDao.deleteStudent();
        }).start();
    }

    /**修改学生*/
    public void updateData(Student student){
        new Thread(()->{
            studentDao.updateStudent(student);
        }).start();
    }

    /**通过名字查询表中某数据是否存在 存在就返回1 不存在就返回0*/
    public int studentExitByName(String name){
        return studentDao.studentNameExits(name);
    }

    /**通过id查询表中某数据是否存在 存在就返回1 不存在就返回0*/
    public int studentExitById(int id){
        return studentDao.studentIdExits(id);
    }

    /**通过名字查询学生*/
    public List<Student> getStudentByName(String name){
        return studentDao.getStudentByName(name);
    }

    /**通过年龄查询学生*/
    public List<Student> getStudentByAge(String age){
        return studentDao.getStudentByAge(age);
    }

    /**通过Id查询数据*/
    public Student queryDataById(int id){
        return studentDao.getStudentById(id);
    }

    /**通过比较id大于某个值查询数据*/
    public List<Student> getStudentThanId(int id){
        return studentDao.getStudentThanId(id);
    }

    /**通过给定的ID范围查询学生*/
    public List<Student> getStudentBetweenId(int minId,int maxId){
        return studentDao.getStudentBetweenId(minId, maxId);
    }

    /**查询数据*/
    public LiveData<List<Student>> getAllData(){
        return studentDao.getStudentList();
    }

}
