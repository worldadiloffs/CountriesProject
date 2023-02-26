package uz.itschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.itschool.databinding.ActivityFavBinding
import uz.itschool.databinding.ActivityMainBinding

class FavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavBinding
    var list = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val country = intent.getSerializableExtra("fav") as User
        list.add(country)

        var adapter = FavAdapter(this, list)
        binding.main.adapter = adapter

        binding.main.setOnItemClickListener { _, _, i, _ ->
            var user = list.get(i)
            var intent = Intent(this, MoreInfoActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }
}