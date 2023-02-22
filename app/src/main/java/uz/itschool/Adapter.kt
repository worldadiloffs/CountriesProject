package uz.itschool

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import coil.load
import coil.transform.CircleCropTransformation
import uz.itschool.databinding.ItemUserBinding

class Adapter(context: Context, var users: MutableList<User>) :
    ArrayAdapter<User>(context, R.layout.item_user, users) {
    override fun getCount(): Int {
        return users.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var binding: ItemUserBinding
        if (convertView == null) {
            binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }else{
            binding= ItemUserBinding.bind(convertView)
        }
        val user = users.get(position)
        binding.img.load(user.img){
            placeholder(R.drawable.ic_launcher_background)
            error(androidx.appcompat.R.drawable.abc_btn_radio_material_anim)
            transformations(CircleCropTransformation())
        }
        binding.name.text = user.name
        binding.population.text = user.population
        binding.area.text = user.area

        return binding.root
    }
}