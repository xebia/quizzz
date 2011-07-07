#!/bin/bash
#   Birds of India
#From http://en.wikipedia.org/wiki/List_of_birds_of_India


function download () {
    name="$1"
    url="$2"
    wget -O "$name".jpg "$url"
}

download "Little Grebe" "http://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Zwergtaucher_060319_3.jpg/200px-Zwergtaucher_060319_3.jpg"
download "Wilson's Storm Petrel" "http://upload.wikimedia.org/wikipedia/commons/thumb/6/61/Oceanites_oceanicusPCCA20070623-3634B.jpg/200px-Oceanites_oceanicusPCCA20070623-3634B.jpg"
download "Red-tailed Tropicbird" "http://upload.wikimedia.org/wikipedia/commons/thumb/5/5b/Phaethon_rubricauda_Europa_Island.jpg/200px-Phaethon_rubricauda_Europa_Island.jpg"
download "Great White Pelican" "http://upload.wikimedia.org/wikipedia/commons/thumb/9/92/Pelican.great.white.arp.750pix.jpg/200px-Pelican.great.white.arp.750pix.jpg"
download "Brown Boody" "http://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/Brown_booby.jpg/200px-Brown_booby.jpg"
download "Great Cormorant" "http://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Great_Cormarant_AE.JPG/200px-Great_Cormarant_AE.JPG"
download "Great Frigatebird" "http://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/NPSgreaterfrigatebird.jpg/200px-NPSgreaterfrigatebird.jpg"
download "Little Bittern" "http://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Ixobrychus_minutus_2_%28Marek_Szczepanek%29.jpg/220px-Ixobrychus_minutus_2_%28Marek_Szczepanek%29.jpg"
download "Eastern Great Egret" "http://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/Great_Egret_%28Casmerodius_albus%29-_Breeding_plumage_at_Sultanpur_I_Picture_257.jpg/220px-Great_Egret_%28Casmerodius_albus%29-_Breeding_plumage_at_Sultanpur_I_Picture_257.jpg"
download "Black-necked Stork" "http://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Black_necked_Stork_I-Bharatpur_IMG_8534.jpg/200px-Black_necked_Stork_I-Bharatpur_IMG_8534.jpg"
download "Eurasian Spoonbilll" "http://upload.wikimedia.org/wikipedia/commons/thumb/1/10/Eurasian_Spoonbill_%28Platalea_leucorodia%29_at_Bharatpur_I_IMG_5670.jpg/200px-Eurasian_Spoonbill_%28Platalea_leucorodia%29_at_Bharatpur_I_IMG_5670.jpg"




