namespace AndroidApp2
{
    [Activity(Label = "@string/app_name", MainLauncher = true)]
    public class MainActivity : Activity
    {
        protected override void OnCreate(Bundle? savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            SetContentView(Resource.Layout.activity_main);

            EditText editText1 = FindViewById<EditText>(Resource.Id.editText1);
            Button button1 = FindViewById<Button>(Resource.Id.button1);
            TextView textView1 = FindViewById<TextView>(Resource.Id.textView1);

            long Factorial(int n)
            {
                if (n == 0) return 1;
                return n * Factorial(n - 1);
            }

            button1.Click += delegate
            {
                if (string.IsNullOrEmpty(editText1.Text))
                {
                    textView1.Text = "Wprowadz liczbe";
                    return;
                }

                if (!int.TryParse(editText1.Text, out int inputValue))
                {
                    textView1.Text = "Wprowadz liczbe calkowita";
                    return;
                }

                try
                {
                    long factorial = Factorial(inputValue);
                    textView1.Text = "Silnia: " + factorial.ToString();
                }
                catch (ArgumentException e)
                {
                    textView1.Text = e.Message;
                }
            };
        }
    }
}
