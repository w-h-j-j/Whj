package com.mingrisoft.whj.db.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao          //定义一个Dao接口文件，以完成对Entity的访问
public interface StudentDao {

    /**添加学生*/
    @Insert
    void insertStudent(Student student);

    /**删除学生*/
    @Delete
    void deleteStudent(Student student);

    /**通过名字删除学生*/
    @Query("DELETE FROM student WHERE name = :name")
    void deleteStudent(String name);

    /**删除表上的所有学生*/
    @Query("DELETE FROM student")
    void deleteStudent();

    /**修改学生*/
    @Update
    void updateStudent(Student student);

    /**通过名字查询表中某数据是否存在 存在就返回1 不存在就返回0*/
    @Query("SELECT 1 FROM student where name=:name limit 1")
    int studentNameExits(String name);

    /**通过id查询表中某数据是否存在 存在就返回1 不存在就返回0*/
    @Query("SELECT 1 FROM student where id=:id limit 1")
    int studentIdExits(int id);

    /**通过id范围查询*/
    @Query("SELECT * FROM student WHERE id BETWEEN :minID AND :maxID")
    List<Student> getStudentBetweenId(int minID, int maxID);

    /**通过比较id大于某个值查询学生*/
    @Query("SELECT * FROM student WHERE id >= :id")
    List<Student> getStudentThanId(int id);

    /**获取所有学生*/
    @Query("SELECT * FROM student")
    LiveData<List<Student>> getStudentList();

    /**通过Id查询学生*/
    @Query("SELECT * FROM student WHERE id = :id")
    Student getStudentById(int id);

    /**通过名字查询学生*/
    @Query("SELECT * FROM student WHERE name = :name")
    List<Student> getStudentByName(String name);

    /**通过年龄查询学生*/
    @Query("SELECT * FROM student WHERE age = :age")
    List<Student> getStudentByAge(String age);
}
