# l_32-java-android.-SharedPreference---2--qism.-Amaliyot
SharedPreference, Gson, ActivityResultLauncher.

Amaliyot uchun , ro'yhatdan o'tish qismini qilish.

 //gson
    implementation 'com.google.code.gson:gson:2.9.1'

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String userName = data.getStringExtra("userName");
                        String password = data.getStringExtra("password");
                        editText1.setText(userName);
                        editText2.setText(password);
                    }
                }
            });
            
            ![Screenshot_1659910891](https://user-images.githubusercontent.com/110789833/183313468-10c0b9e7-766c-4234-b4db-a87b2891bc8d.png)

          

    
