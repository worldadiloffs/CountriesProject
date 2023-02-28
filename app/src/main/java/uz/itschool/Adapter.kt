package uz.itschool

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import uz.itschool.databinding.ActivityMainBinding
import uz.itschool.databinding.ItemUserBinding

class Adapter(context: Context, var users: MutableList<User>,  var binding2: ActivityMainBinding) :
    ArrayAdapter<User>(context, R.layout.item_user, users) {

    private var isFav = false
    override fun getCount(): Int {
        return users.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var binding: ItemUserBinding
        if (convertView == null) {
            binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else {
            binding = ItemUserBinding.bind(convertView)
        }
        val user = users.get(position)
        binding.img.load(user.img) {
            placeholder(R.drawable.ic_launcher_background)
            error(androidx.appcompat.R.drawable.abc_btn_radio_material_anim)
            transformations(CircleCropTransformation())
        }
        binding.delete.setOnClickListener {
            users.remove(user)
            notifyDataSetChanged()
        }
        binding.edit.setOnClickListener {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.custom_dialog)
            val name = dialog.findViewById<TextView>(R.id.name)
            name.text = user.name
            val population = dialog.findViewById<TextView>(R.id.population)
            population.text = user.population
            val change = dialog.findViewById(R.id.change) as Button
            change.setOnClickListener {
                user.name = name.text.toString()
                user.population = population.text.toString()
                notifyDataSetChanged()
                dialog.dismiss()
            }
            dialog.show()
        }

        if (users.get(position).status) binding.fav.setImageResource(R.drawable.fav)
        else binding.fav.setImageResource(R.drawable.fav_un)

        binding.fav.setOnClickListener {
            if (users.get(position).status){
                binding.fav.setImageResource(R.drawable.fav_un)
                users.get(position).status = false
                if (binding2.fav.tag == 1){
                    users.removeAt(position)
                    notifyDataSetChanged()
                }
                return@setOnClickListener
            }
            binding.fav.setImageResource(R.drawable.fav)
            users.get(position).status = true
        }
        binding.name.text = user.name
        binding.population.text = user.population
        binding.area.text = user.area

        return binding.root
    }
}