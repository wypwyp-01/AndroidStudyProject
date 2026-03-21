package fragment

import android.Manifest
import android.content.Context.MODE_PRIVATE
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.wyp.studyproject.R
import com.wyp.studyproject.databinding.ActivityMainBinding
import com.wyp.studyproject.databinding.SharedpreferenceTestBinding


class SharedPreferenceFragment: Fragment() {
    private lateinit var binding: SharedpreferenceTestBinding
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


    }


}