package com.my.MathJS.example;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.LinearLayout;
import java.util.ArrayList;
import android.widget.TextView;
import android.widget.ScrollView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

public class MainActivity extends AppCompatActivity {
	
	
	private Toolbar _toolbar;
	private FloatingActionButton _fab;
	private DrawerLayout _drawer;
	private String HTMLifiy = "";
	private String precision = "";
	private double display_counter = 0;
	private String encoded_url = "";
	private String temp = "";
	private String equationRequest = "";
	private boolean listening = false;
	
	private ArrayList<String> Display = new ArrayList<>();
	private ArrayList<String> Request = new ArrayList<>();
	private ArrayList<String> History = new ArrayList<>();
	
	private LinearLayout linear9;
	private TextView equation;
	private TextView display;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private TextView voice_input;
	private TextView left_bracket;
	private TextView right_bracket;
	private TextView clear;
	private TextView seven;
	private TextView eight;
	private TextView nine;
	private TextView divide;
	private TextView four;
	private TextView five;
	private TextView six;
	private TextView multiply;
	private TextView one;
	private TextView two;
	private TextView three;
	private TextView minus;
	private TextView textview22;
	private TextView zero;
	private TextView decimal_point;
	private TextView plus;
	private ScrollView _drawer_vscroll1;
	private LinearLayout _drawer_linear1;
	private TextView _drawer_textview1;
	private TextView _drawer_textview2;
	
	private RequestNetwork MathJS;
	private RequestNetwork.RequestListener _MathJS_request_listener;
	private AlertDialog.Builder onErrorResponse;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_fab = (FloatingActionButton) findViewById(R.id._fab);
		
		_drawer = (DrawerLayout) findViewById(R.id._drawer);ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(MainActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		equation = (TextView) findViewById(R.id.equation);
		display = (TextView) findViewById(R.id.display);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		voice_input = (TextView) findViewById(R.id.voice_input);
		left_bracket = (TextView) findViewById(R.id.left_bracket);
		right_bracket = (TextView) findViewById(R.id.right_bracket);
		clear = (TextView) findViewById(R.id.clear);
		seven = (TextView) findViewById(R.id.seven);
		eight = (TextView) findViewById(R.id.eight);
		nine = (TextView) findViewById(R.id.nine);
		divide = (TextView) findViewById(R.id.divide);
		four = (TextView) findViewById(R.id.four);
		five = (TextView) findViewById(R.id.five);
		six = (TextView) findViewById(R.id.six);
		multiply = (TextView) findViewById(R.id.multiply);
		one = (TextView) findViewById(R.id.one);
		two = (TextView) findViewById(R.id.two);
		three = (TextView) findViewById(R.id.three);
		minus = (TextView) findViewById(R.id.minus);
		textview22 = (TextView) findViewById(R.id.textview22);
		zero = (TextView) findViewById(R.id.zero);
		decimal_point = (TextView) findViewById(R.id.decimal_point);
		plus = (TextView) findViewById(R.id.plus);
		_drawer_vscroll1 = (ScrollView) _nav_view.findViewById(R.id.vscroll1);
		_drawer_linear1 = (LinearLayout) _nav_view.findViewById(R.id.linear1);
		_drawer_textview1 = (TextView) _nav_view.findViewById(R.id.textview1);
		_drawer_textview2 = (TextView) _nav_view.findViewById(R.id.textview2);
		MathJS = new RequestNetwork(this);
		onErrorResponse = new AlertDialog.Builder(this);
		
		voice_input.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "to be implemented in version 1.1");
			}
		});
		
		left_bracket.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add("(");
				Request.add("(");
				_updateDisplay();
			}
		});
		
		right_bracket.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add(")");
				Request.add(")");
				_updateDisplay();
			}
		});
		
		clear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.remove((int)(Display.size() - 1));
				Request.remove((int)(Request.size() - 1));
				_updateDisplay();
			}
		});
		
		seven.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add("7");
				Request.add("7");
				_updateDisplay();
			}
		});
		
		eight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add("8");
				Request.add("8");
				_updateDisplay();
			}
		});
		
		nine.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add("9");
				Request.add("9");
				_updateDisplay();
			}
		});
		
		divide.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add(" ÷ ");
				Request.add("/");
				_updateDisplay();
			}
		});
		
		four.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add("4");
				Request.add("4");
				_updateDisplay();
			}
		});
		
		five.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add("5");
				Request.add("5");
				_updateDisplay();
			}
		});
		
		six.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add("6");
				Request.add("6");
				_updateDisplay();
			}
		});
		
		multiply.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add(" × ");
				Request.add("*");
				_updateDisplay();
			}
		});
		
		one.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add("1");
				Request.add("1");
				_updateDisplay();
			}
		});
		
		two.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add("2");
				Request.add("2");
				_updateDisplay();
			}
		});
		
		three.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add("3");
				Request.add("3");
				_updateDisplay();
			}
		});
		
		minus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add(" - ");
				Request.add("-");
				_updateDisplay();
			}
		});
		
		textview22.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "to be implemented in version 1.1");
			}
		});
		
		zero.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add("0");
				Request.add("0");
				_updateDisplay();
			}
		});
		
		decimal_point.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add(".");
				Request.add(".");
				_updateDisplay();
			}
		});
		
		plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Display.add(" + ");
				Request.add("+");
				_updateDisplay();
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				display_counter = 0;
				equationRequest = "";
				for(int _repeat12 = 0; _repeat12 < (int)(Request.size()); _repeat12++) {
					equationRequest = equationRequest.concat(Request.get((int)(display_counter)));
					display_counter++;
				}
				_encode_URL(equationRequest);
				MathJS.startRequestNetwork(RequestNetworkController.GET, "https://api.mathjs.org/v4/".concat("?expr=".concat(encoded_url.concat("&precision=".concat(precision)))), "1", _MathJS_request_listener);
			}
		});
		
		_MathJS_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _response = _param2;
				display.setText(_response);
				equation.setText("");
				display_counter = 0;
				for(int _repeat13 = 0; _repeat13 < (int)(Display.size()); _repeat13++) {
					equation.setText(equation.getText().toString().concat(Display.get((int)(display_counter))));
					display_counter++;
				}
				History.add(equation.getText().toString());
				History.add(_response);
				Display.clear();
				Request.clear();
				clear.setEnabled(false);
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				onErrorResponse.setTitle("Houston We Have a Problem!");
				onErrorResponse.setMessage("An internet connection is required to use this app and the math.js API has a rate limit of 1 request every 10 seconds.\n\n".concat(_message));
				onErrorResponse.create().show();
				onErrorResponse.setNegativeButton("Back", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						//onErrorResponse.dismiss();
					}
				});
				onErrorResponse.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						display_counter = 0;
						equationRequest = "";
						for(int _repeat35 = 0; _repeat35 < (int)(Request.size()); _repeat35++) {
							equationRequest = equationRequest.concat(Request.get((int)(display_counter)));
							display_counter++;
						}
						_encode_URL(equationRequest);
						MathJS.startRequestNetwork(RequestNetworkController.GET, "https://api.mathjs.org/v4/".concat("?expr=".concat(encoded_url.concat("&precision=".concat(precision)))), "1", _MathJS_request_listener);
					}
				});
				onErrorResponse.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						finish();
					}
				});
				onErrorResponse.create().show();
			}
		};
	}
	private void initializeLogic() {
		precision = "2";
		display.setTextIsSelectable(true);
		equation.setTextIsSelectable(true);
		listening = false;
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (_drawer.isDrawerOpen(GravityCompat.START)) {
			_drawer.closeDrawer(GravityCompat.START);
		}
		else {
			super.onBackPressed();
		}
	}
	private void _updateDisplay () {
		display.setText("");
		display_counter = 0;
		for(int _repeat10 = 0; _repeat10 < (int)(Display.size()); _repeat10++) {
			display.setText(display.getText().toString().concat(Display.get((int)(display_counter))));
			display_counter++;
		}
		clear.setEnabled(true);
	}
	
	
	private void _encode_URL (final String _url) {
		encoded_url = "";
		try{
			encoded_url = urlEncoder.encode(_url, "UTF-8");
		} catch (Exception e){}
	} public java.net.URLEncoder urlEncoder;
	{
		//DON'T PLACE ANYTHING BELOW THIS BLOCK, made by Amitoj
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
