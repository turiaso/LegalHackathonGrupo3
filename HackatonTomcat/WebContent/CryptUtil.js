function encrypt(text){
	var s = new String();
    for (var i = 0; i < text.length; ++i){
	   s = s.concat(BitArray.shred(text.charCodeAt(i)).pushOff(8).toString());
    } 
	var offusque = new String();
	for(var i in s){
		offusque = offusque.concat(s[i]);
		offusque = offusque.concat(Math.round(Math.random()));
	}
		
    var out = new String();	
	var auxByte = new String();
	for(var i in offusque){
		auxByte = auxByte.concat(offusque[i]);
		if(i%8 == 7){
			var number = (BitArray.fromString(auxByte).toNumber()+ 31) % 10176;
			out = out.concat(String.fromCharCode(number));
			auxByte = new String();
		}
	}
		
    return Base64.encode(out);
}

function decrypt(text){
	
	var textDecoded = Base64.decode(text);
	var offusque = new String();
    for (var i = 0; i < textDecoded.length; ++i){
	   offusque = offusque.concat(BitArray.shred((10176 + textDecoded.charCodeAt(i) - 31) % 10176).pushOff(8).toString());
    } 
	
	var desOffusque = new String();
	for(var i in offusque){
		if(i%2 == 0){
			desOffusque = desOffusque.concat(offusque[i]);
		}
	}	
	var out = new String();	
	var auxByte = new String();
	for(var i in desOffusque){
		auxByte = auxByte.concat(desOffusque[i]);
		if(i%8 == 7){
			out = out.concat(String.fromCharCode(BitArray.fromString(auxByte).toNumber()));
			auxByte = new String();
		}
	}
		
    return out;
	
}