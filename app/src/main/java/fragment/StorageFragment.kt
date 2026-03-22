package fragment

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import com.wyp.studyproject.databinding.SharedpreferenceTestBinding


class StorageFragment: Fragment() {
    private lateinit var binding: SharedpreferenceTestBinding
    private val mDatabaseName by lazy { requireActivity().getFilesDir().absolutePath + "/test.db" }
    lateinit var dbHelper: UserDBHelper
    lateinit var db: SQLiteDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SharedpreferenceTestBinding .inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = UserDBHelper(requireContext())
        db = dbHelper.writableDatabase
        // 找到控件
        binding.buttonSave.setOnClickListener {
            val name = binding.editName.text.toString()
            val age = binding.editAge.text.toString()
            val sp = requireActivity().getSharedPreferences("userinfo",MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("name",name)
            if (name == "" || age == "") {
                Toast.makeText(requireContext(),"请输入完整信息",LENGTH_LONG).show()
            } else {
                editor.putInt("age",age.toInt())
                editor.putFloat("height",182.0f)
                editor.putBoolean("marry",false)
                editor.commit()
                Toast.makeText(requireContext(),"已保存",LENGTH_LONG).show()
            }
        }
        binding.buttonGet.setOnClickListener {
            val sp = requireActivity().getSharedPreferences("userinfo",MODE_PRIVATE)
            val name = sp.getString("name","default")
            val age = sp.getInt("age",0)
            val height = sp.getFloat("height",182f)
            val marry = if(sp.getBoolean("marry",false)) "是" else "否"
            Toast.makeText(requireContext(),"姓名：$name,年龄：$age,身高：$height,婚否：$marry",LENGTH_LONG).show()
        }

        binding.buttonSqliteCreate.setOnClickListener {
            // 创建  打开数据库
            val database = requireActivity().openOrCreateDatabase(mDatabaseName,Context.MODE_PRIVATE,null)
            Toast.makeText(requireContext(),"${database.path},创建 ${if (database == null) "失败" else "成功"}",LENGTH_LONG).show()
        }

        binding.buttonSqliteDelete.setOnClickListener {
            val result = requireActivity().deleteDatabase(mDatabaseName)
            Toast.makeText(requireContext(),"${mDatabaseName} 删除 ${if (result) "成功" else "失败"}",LENGTH_LONG).show()
        }

        binding.buttonSqliteEdit.setOnClickListener {
            insertValue(db)
        }

        binding.buttonSqliteDeitDelete.setOnClickListener {
            // db.delete("user_info","age>?",arrayOf("10"))
            // 删除所有
            // db.delete("user_info","1=1",null)
            // Toast.makeText(requireContext(),"数据删除成功",Toast.LENGTH_LONG).show()

            // 修改
            val insertValue = ContentValues().apply{
                this.put("name","wyp")
                this.put("age",20)
            }
            // db.update("user_info",insertValue,"age<=?",arrayOf("10"))
            // db.execSQL("update user_info set age = age + 20 where age <= 10")

            // 查询语句，返回值是一个游标
            // 查询所有，没有条件
            val cursor = db.query("user_info",null,null,null,null,null,null)
            while (cursor.moveToNext()) {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val age = cursor.getInt(2)
                val weight = cursor.getFloat(3)
                val height = cursor.getFloat(4)
                Log.d("test","id:$id,name:$name,age:$age,weight:$weight,height:$height")
            }
            cursor.close()
        }










    }


    private fun insertValue(db: SQLiteDatabase) {
        db.beginTransaction()
        try {
            for (i in (1..10)) {
                val insertValue = ContentValues().apply{
                    this.put("name","wyp$i")
                    this.put("age",i)
                    this.put("height",i * 0.1f + 170f)
                    this.put("weight",i * 0.1f + 120f)
                }
                db.insert("user_info",null,insertValue)
            }
            db.setTransactionSuccessful()
            Toast.makeText(requireContext(),"数据插入成功",Toast.LENGTH_SHORT).show()
        } finally {
            db.endTransaction()
            Toast.makeText(requireContext(),"事务结束",Toast.LENGTH_SHORT).show()
        }

        // 使用原始SQL语句插入数据
        db.beginTransaction()
        try {
            db.execSQL("INSERT INTO user_info(name, age, height, weight) VALUES ('Tom1', 21, 171, 61)")
            db.execSQL("INSERT INTO user_info(name, age, height, weight) VALUES ('Tom2', 22, 172, 62)")
            db.execSQL("INSERT INTO user_info(name, age, height, weight) VALUES ('Tom3', 23, 173, 63)")
            db.setTransactionSuccessful()
            Toast.makeText(requireContext(),"数据插入成功",Toast.LENGTH_SHORT).show()
        } finally {
            db.endTransaction()
            Toast.makeText(requireContext(),"事务结束",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
        dbHelper.close()
    }
}
