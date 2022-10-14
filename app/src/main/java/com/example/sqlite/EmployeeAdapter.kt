package com.example.sqlite
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite.databinding.EmployeeBinding
import com.example.sqlite.databinding.EmployeeUpdateBinding

class EmployeeAdapter(var aCtx:Context,var resourse:Int,var list:List<Employee>)
    : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>(){
    internal var helper=DatabaseHelper(aCtx)
    class ViewHolder(binding: EmployeeBinding) : RecyclerView.ViewHolder(binding.root) {
        var tvFirstname: TextView = binding.tvFirstname
        var tvLastname:TextView =binding.tvLastname
        var tvId:TextView =binding.tvId

        var update:Button=binding.btnUpdate
        var delete:Button=binding.btnDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = EmployeeBinding.inflate(LayoutInflater.from(aCtx), parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvFirstname.text =list[position].firstname
        holder.tvLastname.text =list[position].lastname
        holder.tvId.text =list[position].id
        holder.update.setOnClickListener {
            updateInfo(list[position])
        }
        holder.delete.setOnClickListener {
            delete(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun delete(employee: Employee)
    {
        helper.deleteData(employee.id)
    }
    fun updateInfo(employee: Employee)
    {

//     var edFirstnameUpdate: EditText = binding.edFirstnameUpdate
//        var edLastnameUpdate:EditText =binding.edLastnameUpdate
//        var edAgeUpdate:EditText =binding.edAgeUpdate
//        var edAddressUpdate:EditText =binding.edAddressUpdate
//        var edDepartmentUpdate:EditText =binding.edDepartmentUpdate

        val builder=AlertDialog.Builder(aCtx)
        builder.setTitle("Update Info")
      val view1 = EmployeeUpdateBinding.inflate(LayoutInflater.from(aCtx))
        view1.apply {
            edFirstnameUpdate.setText(employee.firstname)
            edLastnameUpdate.setText(employee.lastname)
            edAgeUpdate.setText(employee.age)
            edAddressUpdate.setText(employee.address)
            edDepartmentUpdate.setText(employee.department)
        }
        builder.setView(view1.root)
        builder.setPositiveButton("Update",object:DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                view1.apply {

                    val isUpdate=helper.updateData(employee.id,
                        edFirstnameUpdate.text.toString().trim(),
                        edLastnameUpdate.text.toString().trim(),
                        edAgeUpdate.text.toString().trim(),
                        edAddressUpdate.text.toString().trim(),
                        edDepartmentUpdate.text.toString().trim())
                    if(isUpdate)
                        Toast.makeText(aCtx,"Updated :)",Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(aCtx,"Failed update :(",Toast.LENGTH_LONG).show()
                }

            }

        })
        builder.setNegativeButton("Cancel",object:DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }

        })
        val alert= builder.create()
        alert.show()


    }
}

