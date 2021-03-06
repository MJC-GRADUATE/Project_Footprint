package kr.ac.mjc.footprint.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import de.hdodenhof.circleimageview.CircleImageView
import kr.ac.mjc.footprint.CommunityDetailActivity
import kr.ac.mjc.footprint.Data.Post
import kr.ac.mjc.footprint.R
import kr.ac.mjc.footprint.Data.User

class AddAdapter(var context: Context, var postList:ArrayList<Post>): RecyclerView.Adapter<AddAdapter.ViewHolder>() {

    var onItemClickListener: OnItemClickListener?=null

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView!!){

        var titleTv: TextView = itemView.findViewById(R.id.title_tv)
        var textTv2: TextView =itemView.findViewById(R.id.text_tv2)
        var dateItem: TextView = itemView.findViewById(R.id.item_date)

        var writerTv: TextView = itemView.findViewById(R.id.writer_tv)
        var profileIv3: CircleImageView = itemView.findViewById(R.id.profile_iv3)

        var circleView: View = itemView.findViewById(R.id.circle_view)
        var emailTv: TextView = itemView.findViewById(R.id.email_tv)

        var user= User()

        fun bind(post: Post){

            var auth = FirebaseAuth.getInstance().currentUser

            if (emailTv.text != auth?.email.toString()) {
                circleView.setBackgroundResource(R.drawable.red_circle_view)
            }else{
                circleView.setBackgroundResource(R.drawable.green_circle_view)
            }

            titleTv.text = post.textTitleEt
            textTv2.text = post.contentEt
            dateItem.text = post.uploadDate.toString()

            var firestore = FirebaseFirestore.getInstance()
            firestore.collection("User").document(post.userId).get()
                .addOnSuccessListener {
                    var user = it.toObject(User::class.java)
                    Glide.with(profileIv3).load(user?.profileUrl).into(profileIv3)
                    writerTv.text = user?.name
                    emailTv.text = user?.email

                }

            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(post)
                val intent = Intent(itemView?.context, CommunityDetailActivity::class.java)
                intent.putExtra("id",post.id)
                startActivity(itemView?.context, intent, null)
            }

        }
    }

    interface OnItemClickListener{
        fun onItemClick(post: Post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //??????????????? ???????????? ??????
        var view = LayoutInflater.from(context).inflate(R.layout.item_community,parent,false)
        //????????? ??????????????? ???????????? ??????
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
        //????????? ??????????????? ?????????..
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //???????????? ??????????????? ??????.
        var post=postList[position]
        holder.bind(post)
    }

}