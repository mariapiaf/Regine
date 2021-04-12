package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {

	private int N;
	private List<List<Integer>> soluzioni;
	// N è il numero di righe e colonne della scacchiera
	//   (righe e colonne numerate da 0 a N-1)
	// ad ogni livello posizioniamo una regina in una nuova riga
	
	// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
	// 		List<Integer>
	// livello = quante righe sono già piene
	// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
	// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3, ossia la quarta
	// [0]
	//     [0, 2]
	//            [0, 2, 1]
	
	public List<List<Integer>> risolvi(int N){ // ritorna la prima soluzione trovata
		this.N = N;
		List<Integer> parziale = new ArrayList<Integer>();
		this.soluzioni = new ArrayList<>();
		
		cerca(parziale, 0);
		
		return this.soluzioni;
	}
	
	
	// cerca == true : trovato; cerca == false: cerca ancora
	private void cerca(List<Integer>parziale, int livello) {
		if(livello==N) { // caso terminale
			//System.out.println(parziale);
			this.soluzioni.add(new ArrayList<>(parziale));

		} 
		else {
			for(int colonna=0; colonna<N; colonna++) {
				// if la mossa nella casella [livello][colonna] è valida
				// se sì, aggiungi a parziale e fai ricorsione
				// qui provo a posizionare la regina in tutte le colonne possibili
				// ci sono N ipotesi possibili, in cui posso pensare di posizionare la regina
				// dovrei avere una funziona che mi dica se la posizione è valida data la soluzione
				// parziale e la colona dove voglio inserire la nuova regina
				
				if(posValida(parziale, colonna)) { // faccio ricorsione
					parziale.add(colonna); // es. colonna 0. se è valida la nuova posizione, la posso aggiungere alla lista delle soluzioni parziali
					cerca(parziale, livello+1);
					parziale.remove(parziale.size()-1); // backtracking
					// dato che io voglio inserire tutte le colonne possibii per una singola riga, ogni volta
					// che aggiungo una possibile soluzione alla lista parziale, prima di poterne cercare un'altra,
					// devo eliminare dalla lista parziale la soluzione possibile precedentemente trovata
				}
			}
		}
	}


	private boolean posValida(List<Integer> parziale, int colonna) {
		int livello = parziale.size();
		// controlla se viene mangiata in verticale
		if(parziale.contains(colonna))
			return false;
		
		//controlla se viene mangiata in diagonale
		// le diagonali hanno la proprietà che riga + colonna = costante (per le diagonali verso il basso)
		// (per quelle all'indietro) riga-colonna = costante
		// se trovo almeno una regina che ha lo stesso valore costante della regina che sto per posizionare, allora non va bene
		// devo confrontare la posizione [livello, colonna] con (r,c) delle regine già esistenti
		for(int r=0; r<livello; r++) {
			int c = parziale.get(r); // così data una riga, ricavo la colonna in cui ho già posizionato una regina
		
			if(r+c == livello+colonna || r-c == livello-colonna)
				return false;
		}
		
		return true;
	}


	/*
	 * SOLUZIONE
	 * creo una lista di posizioni che indica la colonna in cui si trova la regina
	 * le righe invece sono date dall'elemento della list che sto considerando. 
	 * Con questo ho potuto considerare il fatto che in ogni riga ci sarà solo una regina
	 * Come posso partire da una soluzione vuota e cominciare a popolarla?
	 * oppure come faccio a partire da una soluzione parziale e falla crescere?
	 * Ci sono modi diversi di costruire una soluzione:
	 * 1: Il livello della ricorsione coincide con il numero di righe già piene.
	 * 2: Il livello indica il numero delle regine
	 * Consideriamo numero 1.
	 * quando sono al livello 4, devo riempire la posizione 4 (posizione intesa come riga, ossia la quinta).
	 * Man mano che costruisco delle soluzioni, devo verificare che quello che abbiamo scaleto rispetti 
	 * tutti i vincoli.
	 * Alla ricorsione, io devo verificare di poter aggiungere in quella riga, la singola colonna, e quindi 
	 * ottengo tutte le posizioni possibili.
	 * Cosa mi può impedire di trovare una soluzione?
	 * Se in tutte le posizioni possibili di colonna c'è qualche vincolo che mi impedisce di posizionarla.
	 * se è così si deve tornare indietro e riposizionare quelle precedenti.
	 * 
	 * all'inizio, parziale sarà vuota e livello sarà pari a 0.
	 * 
	 * 
	 */
	
}
