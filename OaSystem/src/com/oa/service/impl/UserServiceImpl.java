package com.oa.service.impl;

import com.oa.bean.PageInfo;
import com.oa.bean.User;
import com.oa.dao.UserDao;
import com.oa.service.UserService;

import javax.servlet.http.Part;
import java.io.*;
import java.util.List;

//业务层的实现类--实现抽象方法
public class UserServiceImpl implements UserService {

    //获取Dao的对象
    UserDao userDao = new UserDao();

    @Override
    public boolean insertUser(User user, Part part) {
        //1、实现文件上传
        String imgname = null;
        try {
            imgname = upload(part);
        } catch (IOException e) {
            return false;//说明操作失败
        }
        //2、判断是否有图片数据、以及进行数据设置到user对象中
        if(imgname!=null&&!imgname.equals("")){
            user.setImgname(imgname);
        }
        //3、实现数据添加的操作
        return userDao.insertUser(user);
    }

    //定义一个文件上传的方法，返回文件名
    public String upload(Part part) throws IOException {//Part是一个文件区域的对象
        //1、获取文件名
            //--part.getHeader("Content-Disposition")
        String disposition = part.getHeader("Content-Disposition");
        System.out.println(disposition);
            //--获取字符串截取的起始位置
        int start = disposition.indexOf("filename=\"")+10;
//        int start = disposition.lastIndexOf("\\")+1;\\
            //--获取字符串截取的结束位置
        int end = disposition.lastIndexOf("\"");
            //--截取字符串
        String fileName = disposition.substring(start,end);
        System.out.println(fileName);
        // 2、定义一个文件路径用于存放文件
        String path = "E:\\实训\\广大计科\\file";
        // 3、获取来自网络端上传的文件，以流的形式来获取 ：输入流
        BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
        // 4、定义字节缓冲数据，用于缓冲输入流的数据
        byte[] buff = new byte[1024];
            // 定义一个int变量，用于存放单次读取数据的字节个数
        int len = 0;
        // 5、创建文件对象
        File file = new File(path+File.separator+fileName);
        // 6、创建输出流，并通过输出流将文件写到硬盘
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        // 7、进行循环的读写操作
        while((len=bis.read(buff))!=-1){//读取--读取到缓冲字节数组中
            //写出
            bos.write(buff,0,len);
            //刷新流
            bos.flush();
        }
        // 8、关闭流
        bis.close();bos.close();
        //返回文件名
        return fileName;
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user,Part part) {
        //1、实现文件上传
        String imgname = null;
        try {
            imgname = upload(part);
        } catch (IOException e) {
            return false;//说明操作失败
        }
        //2、判断是否有图片数据、以及进行数据设置到user对象中
        if(imgname!=null&&!imgname.equals("")){
            user.setImgname(imgname);
        }
        //3、实现数据修改的操作
        return userDao.updateUser(user);
    }

    @Override
    public List<User> queryUser(PageInfo page) {
        return userDao.queryUser(page);
    }

    @Override
    public User queryUserById(int userid) {
        return userDao.queryUserById(userid);
    }

    @Override
    public User queryUserLogin(String name, String pass) {
        return userDao.queryUserLogin(name,pass);
    }
}
