%===========================================================================================================
% Prädikate zum Programmaufruf
% Von bflow*-Toolbox wird a/0 aufgerufen.
% t muss vor a aufgerufen werden, da durch Reduzierung Informationen verlorengehen können
%===========================================================================================================
% Zum Setzen der Sprache vor dem Aufruf von a/0, b/0, c/0, m/0 oder t/0:
% retractall(language(_)),assertz(language(de))
% a/0 - Semantik- und Stilprüfungen sowie Prüfung der Beschriftungen deutschsprachiger nach eigenschaftserhaltender Reduzierung
% addon_query/2 - wie a/0, aber 1. Parameter - Sprache (Atom: de/en), 2. Parameter - ein Feld mit den Namen der auszuführenden Prüfungen erwartet
% b/0 - Semantik- und Stilprüfungen am Originalmodell (sollte die selben Ergebnisse wie c/0 liefern, ist aber langsamer)
% c/0 - Semantik- und Stilprüfungen nach eigenschaftserhaltender Reduzierung
% m/0 - Metriken
% s/0 - Syntax
% t/0 - Prüfungen der Beschriftungen deutschsprachiger Modelle


addon_query(Sprache,Testliste) :-  (retractall(language(_)),assertz(language(Sprache)),assertz(todo(Testliste)),a,fail);
				print('#query_end#').

a :-  ((s,fail);(m,fail);(t,fail);(analyze0,fail);((re,vprintln('Completely Reduced!'),clause(unstructured,true)
	,vprintln('But not well-structured'));(cache_matches,assertz(match(dummy,dummy)),
	findall(_,analyze,_)))),metric_well_structured.

b :- cache_matches,findall(_,analyze,_).
c :- (analyze0,fail);((re,vprintln('Completely Reduced!'),clause(unstructured,true)
	,vprintln('But not well-structured'));(cache_matches,assertz(match(dummy,dummy)),
	findall(_,analyze,_))).
m :- findall(_,metric,_).
s :- findall(_,analyze_syntax,_).
t :- findall(_,analyze_text,_).

% Prüfen, ob ein Test auszuführen ist.
% Das ist der Fall, wenn todo/1 nicht definiert ist (dann werden alle Tests ausgeführt) oder der Testname im Argument von todo enthalten ist

tocheck(Testname) :- not(clause(todo(_),true));
			(clause(todo(_),true),(todo(List),member(Testname,List))).

%===========================================================================================================
% Formatierung der Ausgabe von Meldungen
%===========================================================================================================

% Um ausführlichere Informationen zu erhalten, verbose setzen
% verbose.

% Um gut lesbare Meldungstexte mit Zeilenubrüchen zu erhalten,
% print_with_pagebreaks setzen
% print_with_pagebreaks.

println(X) :- print(X),((clause(print_with_pagebreaks,true),nl);true).
vprint(X) :- ((clause(verbose,true),print(X));true).
vprintln(X) :- ((clause(verbose,true),print(X),nl);true).

% Meldungsformat für Meldungen auf die Fehlerkonsole bei Bflow:
% ['PROBLEM'|'WARNING'|'INFO'][ID][Text]
message(Class,ID,Text,Orig) :- 	print('addon:[MESSAGE]'),replace_all_substrings(Text,'\n',' ',TextWithoutNewlines),
								print('['),print(Class),print(']'),
				print('['),
				((clause(bflow_id(ID,BflowID),true),
				print(BflowID));true),!,
				print(']'),
				print('['),print(TextWithoutNewlines),print(']'),
				print('['),print(Orig),print(']'),print('#FS#'),nl.

compose_message(Language,Message,List) :- (language(Language),
				   ((length(List,1), Arg = '%t');
				   (length(List,2),  Arg = '%t%t');
				   (length(List,3),  Arg = '%t%t%t');
				   (length(List,4),  Arg = '%t%t%t%t');
				   (length(List,5),  Arg = '%t%t%t%t%t');
				   (length(List,6),  Arg = '%t%t%t%t%t%t');
				   (length(List,7),  Arg = '%t%t%t%t%t%t%t');
				   (length(List,8),  Arg = '%t%t%t%t%t%t%t%t');
				   (length(List,9),  Arg = '%t%t%t%t%t%t%t%t%t');
				   (length(List,10), Arg = '%t%t%t%t%t%t%t%t%t%t');
				   (length(List,11), Arg = '%t%t%t%t%t%t%t%t%t%t%t');
				   (length(List,12), Arg = '%t%t%t%t%t%t%t%t%t%t%t%t');
				   (length(List,13), Arg = '%t%t%t%t%t%t%t%t%t%t%t%t%t');
				   (length(List,14), Arg = '%t%t%t%t%t%t%t%t%t%t%t%t%t%t')
				  ),
				swritef(Message,Arg,List));
				(not(language(Language)),true).
				% Yes - or course this should be defined recursively...


%===========================================================================================================
% Hilfsprädikate für Syntax- und Semantikprüfung
%===========================================================================================================

nodes_between(S,J,Between) :- after([S],J,After),before([J],S,Before),ord_intersection(After,Before,B1),
			ord_add_element(B1,S,B2),ord_add_element(B2,J,Between).

after(Set,Stop,List) :- descend(Set,[],Stop),List = Set.
after(Set,Stop,List) :- descend(Set,New,Stop),New\==[],ord_union(Set,New,Setplus),after(Setplus,Stop,List).

descend(Set,New,Stop) :- findall(X,(member(A,Set),arc(A,X),X\==Stop,not(ord_memberchk(X,Set))),New0),
		list_to_ord_set(New0,New).


before(Set,Stop,List) :- ascend(Set,[],Stop),List = Set.
before(Set,Stop,List) :- ascend(Set,New,Stop),New\==[],ord_union(Set,New,Setplus),before(Setplus,Stop,List).


ascend(Set,New,Stop) :- findall(X,(member(A,Set),arc(X,A),X\==Stop,not(ord_memberchk(X,Set))),New0),
		list_to_ord_set(New0,New).


sese(S,J,After2) :- path(S,J,_),!,after([S],J,After),before([J],S,Before),
		ord_del_element(After,S,After2),ord_del_element(Before,J,Before2),After2==Before2.
sese_region(S,J) :- split(S),join(J),sese(S,J,_).

no_entries(S,J) :- after([S],J,After),before([J],S,Before),
		ord_del_element(After,S,After2),ord_del_element(Before,J,Before2),subset(Before2,After2).

% some useful facts about arcs
uarc(X,Y) :- arc(X,Y);arc(Y,X).
% Die auskommentierte Variante ist ähnlich schnell wie die derzeit benutzte:
% more_than_one_incoming_arcs(X) :- arc(A,X),arc(B,X),A\==B,!.
% more_than_one_outgoing_arcs(X) :- arc(X,A),arc(X,B),A\==B,!.
more_than_one_incoming_arcs(X) :- findall(A,arc(A,X),L),L = [_|L2], L2 \== [].
more_than_one_outgoing_arcs(X) :-  findall(A,arc(X,A),L),L = [_|L2], L2 \== [].

% Für Ereignisse, Funktionen und Prozesswegweiser kann mit no_incoming_arcs getestet werden, ob
% es eingehende / ausgehende Kontrollflusskanten gibt, die der EPK-Syntax entsprechen, also von
% anderen Konnektoren, Ereignissen, Funktionen oder Prozesswegweiser kommen.
% Hat also beispielsweise eine Funktion nur eine eingehende Kontrollflusskante, die (syntaktisch illegal)
% von einer Organisationseinheit kommt, liefert no_incoming_arcs/1 als Ergebnis "true".
no_incoming_arcs(X) :- not(arc(Y,X)),once((event(Y);function(Y);connector(Y),pi(Y))).
no_outgoing_arcs(X) :- not(arc(X,Y)),once((event(Y);function(Y);connector(Y),pi(Y))).

connectors_only([L|Rest]) :- connector(L),!,connectors_only(Rest).
connectors_only([]).
% successor(X,Y) means that X is followed by Y (possibly via some connectors)
successor(X,Y) :- arc(X,Y).
successor(X,Y) :- arc(X,C),connector(C),(not(path_containing_connectors_only(C,C,_))),successor(C,Y).
% types of elements
pi(X)			:- clause(processinterface(X),true).
orconnector(X)  :- clause(or(X),true).
andconnector(X) :- clause(and(X),true).
xorconnector(X) :- clause(xor(X),true).
connector(X)    :- clause(and(X),true) ; clause(or(X),true) ; clause(xor(X),true).
split(X) :- connector(X),more_than_one_outgoing_arcs(X). % When using this, we assume that property8 already has been tested.
join(X) :- connector(X),more_than_one_incoming_arcs(X).  % When using this, we assume that property8 already has been tested.
orsplit(X)      :- orconnector(X),split(X).
andsplit(X)     :- andconnector(X),split(X).
xorsplit(X)     :- xorconnector(X),split(X).
orjoin(X)       :- orconnector(X),join(X).
andjoin(X)      :- andconnector(X),join(X).
xorjoin(X)      :- xorconnector(X),join(X).

type(X,Type)    :- (orconnector(X),Type=or);(andconnector(X),Type=and);(xorconnector(X),Type=xor).
have_same_type(X,Y) :- type(X,TypeX),type(Y,TypeY),TypeX=TypeY.
element(X) :- clause(event(X),true);clause(function(X),true);connector(X).

% Ein Start- oder Endereignis hat entweder gar keinen ein- bzw. ausgehenden Kontrollflusspfeil
% oder aber genau einen, der zu einem Prozesswegweiser führt.
startevent(X) :- event(X),no_incoming_arcs(X).
startevent(X) :- event(X),findall(A,arc(A,X),L),L = [L1|L2], pi(L1), L2 == [].
endevent(X) :- event(X),no_outgoing_arcs(X).
endevent(X) :- event(X),findall(A,arc(X,A),L),L = [L1|L2], pi(L1), L2 == [].

binarysplit(X) :- split(X),findall(Y,arc(X,Y),L),length(L,2).
binaryjoin(X) :- join(X),findall(Y,arc(Y,X),L),length(L,2).


% Pfade und Erreichbarkeit
path(A,B,Path) :-
       travel(A,B,[A],Q), 
       reverse(Q,Path).

travel(A,B,P,[B|P]) :- 
       arc(A,B).
travel(A,B,Visited,Path) :-
       arc(A,C),
       C \== B,
       \+member(C,Visited),
       travel(C,B,[C|Visited],Path).


path_containing_connectors_only(A,B,Path) :-
       travel_connectors_only(A,B,[A],Q), 
       reverse(Q,Path).

travel_connectors_only(A,B,P,[B|P]) :- 
       arc(A,B),connector(B).
travel_connectors_only(A,B,Visited,Path) :-
       arc(A,C),connector(C),
       C \== B,
       \+member(C,Visited),
       travel_connectors_only(C,B,[C|Visited],Path).


secondpath(A,B,Path,Exclude) :-
       travel2(A,B,[A],Q,Exclude), 
       reverse(Q,Path).

travel2(A,B,P,[B|P],_) :- 
       arc(A,B).
travel2(A,B,Visited,Path,Exclude) :-
       arc(A,C),not(ord_memberchk(C,Exclude)),
       C \== B,
       \+member(C,Visited),
       travel2(C,B,[C|Visited],Path,Exclude).


path_without_functions(A,B,Path) :-
       travel3(A,B,[A],Q), 
       reverse(Q,Path).

travel3(A,B,P,[B|P]) :- 
       arc(A,B),not(function(B)).
travel3(A,B,Visited,Path) :-
       arc(A,C),not(function(C)),
       C \== B,
       \+member(C,Visited),
       travel3(C,B,[C|Visited],Path).

twopaths(S,J,Path1,Path2) :- path(S,J,Path1),
	list_to_ord_set(Path1,Path1Set),
	secondpath(S,J,Path2,Path1Set),Path1 \== Path2. % Path1 \== Path2 behandelt den Fall arc(S,J).


twopaths(S,J) :- twopaths(S,J,_,_),!.

findmatch(S,J) :- split(S),join(J),twopaths(S,J).

cache_matches :- findall([S,J],(findmatch(S,J),asserta(match(S,J))),_).

no_sidestep(S,J) :- twopaths(S,J,_,Q), Q == [S,J]. % Pfad Q ist direkte Kante S-> J
no_sidestep(S,J) :- twopaths(S,J,P,Q),  Q \== [S,J], % Pfad Q ist keine direkte Kante S-> J
		P=[S|[PX|_]], % PX ist der Nachfolger von S, der auf P liegt (kann auch J sein)
		reverse(Q,QRev),QRev=[J|[QX|_]], % QX ist der Vorgänger von J, der auf Q liegt
		list_to_ord_set([S,J],Exclude),
		not(secondpath(PX,QX,_,Exclude)), % Es gibt keinen Transfer von P nach Q
		% Im Falle P=[S,J] bedeutet letzte Zeile: Es gibt keinen Downstream-Entry nach Q.
		before([QX],S,BeforeQX),after([S],J,AfterJ),ord_subset(BeforeQX,AfterJ). % kein Einsprung nach !

% Garantiert, dass nicht Transfer in beide Richtungen oder Entry in beiden Pfaden existiert:
% Nur aufrufen, wenn zuvor schon split(S),join(J),match(S,J) geprüft:
no_sidestep_match(S,J) :- no_sidestep(S,J),!.


% Neighbourhood contains all elements of List and their neighbours.
neighbourhood(List,Neighbourhood) :- findall(N,(member(X,List),uarc(X,N)),Neighbours),
                                          union(List,Neighbours,List_And_Neighbours),
                                          list_to_ord_set(List_And_Neighbours,Neighbourhood).

% Find the elements which are weakly connected to at least one element in List
connected_elements(List,X) :- neighbourhood(List,List), X = List.
connected_elements(List,X) :- neighbourhood(List,Neighbourhood), connected_elements(Neighbourhood,X).

a_reaches_b_without_passing_c(A,B,C) :- path(A,B,Path),not(member(C,Path)),!.
every_path_from_a_to_b_must_pass_c(A,B,C) :- not(path(A,B,_));
					(path(A,B,_),not(a_reaches_b_without_passing_c(A,B,C))).
some_path_from_node1_to_an_endevent_does_not_pass_node2(Node1,Node2) :- endevent(E),path(Node1,E,Path),not(member(Node2,Path)),!.

% Beachte: Den Fall, dass kein Pfad vom Startevent oder zum Endevent ex., müssen wir lt. Syntaxdef. nicht betrachten.
every_path_from_node1_to_an_endevent_must_pass_node2(Node1,Node2) :- not(endevent(Node1)),not(some_path_from_node1_to_an_endevent_does_not_pass_node2(Node1,Node2)).
some_path_from_a_startevent_to_node1_does_not_pass_node2(Node1,Node2) :- startevent(S),path(S,Node1,Path),not(member(Node2,Path)),!.
every_path_from_a_startevent_to_node1_must_pass_node2(Node1,Node2) :- not(startevent(Node1)),not(some_path_from_a_startevent_to_node1_does_not_pass_node2(Node1,Node2)).


	
cycle_entry(X) :- 	join(X),path(X,X,Path1),
			% Es gibt einen Pfad von einem Startevent zu X, der außer X selber
			% keine weiteren Knoten aus dem Zyklus durchläuft
			startevent(Start),secondpath(Start,X,_,Path1),!.

cycle_exit(X)  :- 	split(X),path(X,X,Path1),
			% Es gibt einen Pfad von X zu einem Endevent, der außer X selber
			% keine weiteren Knoten aus dem Zyklus durchläuft
			endevent(End),secondpath(X,End,_,Path1),!.

node_successor_not_in_set(E,Set) :- member(E,Set), % ord_memberchk does not work here!
		arc(E,Succ),
		not(ord_memberchk(Succ,Set)),!.

% Exit is a split in nodes_between(...) which has a Successor out of nodes_between(...)
match_with_exit(S,J,E) :- match(S,J),
		nodes_between(S,J,Set),
		split(E),not(andsplit(E)),E\==S, % AND-splits are not regarded as exits.
		node_successor_not_in_set(E,Set).

node_predecessor_not_in_set(E,Set) :- ord_memberchk(E,Set),
		arc(Pred,E),
		not(ord_memberchk(Pred,Set)),!.

match_with_entry(S,J,E) :- match(S,J),
		nodes_between(S,J,Set),
		join(E),E\==J, % Entry ist ein Join in nodes_between(...), der einen Vorgänger außerhalb nodes_between(...) hat
		node_predecessor_not_in_set(E,Set).


match_with_upstream_entry(S,J,E) :- match_with_entry(S,J,E),
			some_path_from_a_startevent_to_node1_does_not_pass_node2(J,S),
			some_path_from_a_startevent_to_node1_does_not_pass_node2(S,E).

% Schließt den Fall aus, dass XOR dafür sorgt, dass ENTWEDER S ODER E erreicht wird:
match_with_true_upstream_entry(S,J,E) :- match_with_upstream_entry(S,J,E),
				     not(xor_relation(S,E)).

match_with_downstream_entry(S,J,E) :- match_with_entry(S,J,E),
				not(match_with_upstream_entry(S,J,E)).

match_without_entry(S,J) :- match(S,J),not(match_with_entry(S,J,_)).
match_from_or(S,J)  :- match(S,J),orsplit(S).
match_from_and(S,J) :- match(S,J),andsplit(S).
match_from_and_with_exit(S,J) :- match_with_exit(S,J,_),andsplit(S).
match_from_xor(S,J) :- match(S,J),xorsplit(S).

% nach dem Mergen von Startereignissen: 
multiple_startevents_before_join(J) :- join(J),arc(S,J),sub_atom(S,_,_,_,merged_startevent).
% Zwischen den vorangehenden Startevents und dem Join liegen noch weitere Knoten:
multiple_startevents_before_join(J) :- join(J),startevent(S1),path(S1,J,Path),
			list_to_ord_set(Path,Path1),
			startevent(S2),S1@<S2,
			secondpath(S2,J,Path2,Path1),Path \== Path2. % Path1 \== Path2 behandelt den Fall arc(S[1-2],J).


% Es reicht, die Fälle zu betrachten, wo X und Y Konnektoren oder Endevents sind
% (1) Split A ist Vorgänger zweier Knoten X und Y.
% (2) der Split trifft auf dem Weg zu diesen Knoten auf
%     keinen Join, der die Pfade A-->X und A-->Y zusammenführt
% (3) Der Weg führt nicht A->X->Y bzw. A->Y->X (wobei die Existenz solcher Pfade nicht verboten ist)

common_ancestor(X,Y,Split) :- 	split(Split),
				(connector(X);endevent(X)),
				(connector(Y);endevent(Y)),
				has_path_to_both(X,Y,Split).


has_path_to_both(X,Y,A) :- path(A,X,P1), list_to_ord_set(P1,P1S),not(ord_memberchk(Y,P1S)),
		secondpath(A,Y,P2,P1S),
		P1 = [_|[AToX|_]],P2 = [_|[AToY|_]],
		not((after([AToX],X,List1),member(Y,List1))),
		not((after([AToY],Y,List2),member(X,List2))),!.

% xor_relation heißt: XOR ist common_ancestor zweier Knoten und auf dem Weg vom XOR-Split gibt es
%  keine "Seiteneingänge"
%  "Seitenausgänge" erlaubt 
% AND und OR sind nicht völlig analog definierbar, denn hier sind Ausgänge interessant.

xor_relation(X,Y) :- xorsplit(A),
		(connector(X);endevent(X)),
		(connector(Y);endevent(Y)),
		has_path_to_both(X,Y,A),
		every_path_from_a_startevent_to_node1_must_pass_node2(X,A),
		every_path_from_a_startevent_to_node1_must_pass_node2(Y,A),!.

delta(S,From,To) :- match(S,To),
		arc(From,To),binarysplit(From),From\==S,
		nodes_between(S,To,Set),
		ord_memberchk(From,Set),
		node_successor_not_in_set(From,Set),
		path(S,From,PathP),
		list_to_ord_set(PathP,PathPSet),
		secondpath(S,To,PathQ,PathPSet),
	 	(
		 arc(S,From)
		 ;
		 (PathP=[S|[P1|[From]]],not(join(P1)))
		 ;
		 (PathP=[S|[P1|_]],reverse(PathP,PathPRev),PathPRev=[From|[P2|_]],no_entries(P1,P2))
		),
		(
		 arc(S,To)
		 ;
		 (PathP=[S|[Q1|[To]]],not(connector(Q1)))
		 ;
		 (PathQ=[S|[Q1|_]],reverse(PathQ,PathQRev),PathQRev=[To|[Q2|_]],sese(Q1,Q2,_))
		).

%===========================================================================================================
% SYNTAXTESTS
%===========================================================================================================
has_events :- clause(event(_),true),!.
has_functions :- clause(function(_),true),!.
has_arcs :- clause(arc(_,_),true),!.

analyze_syntax :- tocheck(syntax2),(has_functions;has_events),has_arcs,	property2.
analyze_syntax :- tocheck(syntax4),has_arcs,				property4a(_,_).
analyze_syntax :- tocheck(syntax4), has_arcs,				property4b(_,_).
analyze_syntax :- tocheck(syntax5),					property5.
analyze_syntax :- tocheck(syntax6),has_functions,has_arcs,		property6(_).
analyze_syntax :- tocheck(syntax7),has_events,has_arcs,			property7(_).
analyze_syntax :- tocheck(syntax8),has_arcs,				property8(_).
analyze_syntax :- tocheck(syntax9),has_arcs,				property9.
analyze_syntax :- tocheck(syntax10),has_events,has_arcs,		property10(_,_).
analyze_syntax :- tocheck(syntax11),has_functions,has_arcs,		property11(_,_).
analyze_syntax :- tocheck(syntax12),has_events,has_arcs,		property12(_).
analyze_syntax :- tocheck(syntax13),has_events,				property13a.
analyze_syntax :- tocheck(syntax13),has_events,				property13b.
analyze_syntax :- tocheck(syntax14),has_events,				property14.
analyze_syntax :- tocheck(syntax15),has_events,				property15.

% Property 1 - The EPC is a directed graph
% Property 2 - The EPC is a coherent graph
property2 :- 	element(E1),connected_elements([E1],Y),!,element(X),not(member(X,Y)),
		compose_message(de,Message,
		['Das Modell ist nicht zusammenhaengend!'
		 ]),
		compose_message(en,Message,
		 ['The model is not a coherent graph!'
		 ]),
		 message('ERROR','',Message,'syntax2').

% Property 3 - The EPC is finite
% Property 4 - There are no multiple arcs between two vertices
property4a(X,Y) :- arc(X,Y),X @< Y,  % X @< Y verhindert doppelte Meldungen
		findall([X,Y],arc(X,Y),L),sort(L,L1),msort(L,L2),L1 \== L2,
		compose_message(de,Message,
		['Zwischen zwei Modellelementen gibt es mehr als einen Pfeil!'
		 ]),
		compose_message(en,Message,
		 ['There is more than one arc between two model elements!'
		 ]),
		 message('ERROR',X,Message,'syntax4').


% The EPC is an antisymmetric graph
property4b(X,Y) :- arc(X,Y),arc(Y,X),X @<Y,
		compose_message(de,Message,
		['Zwischen zwei Modellelementen gibt es mehr als einen Pfeil!'
		 ]),
		compose_message(en,Message,
		 ['There is more than one arc between two model elements!'
		 ]),
		 message('ERROR',X,Message,'syntax4').

% Property 5 - The set of event, the set of functions and the set of arcs are not empty
property5 :- 	not(clause(event(_),true)),
		compose_message(de,Message,
		['Das Modell muss mindestens ein Ereignis enthalten!'
		 ]),
		compose_message(en,Message,
		 ['The model must contain at least one event!'
		 ]),
		 message('ERROR','',Message,'syntax5').

property5 :- 	not(clause(function(_),true)),
		compose_message(de,Message,
		['Das Modell muss mindestens eine Funktion enthalten!'
		 ]),
		compose_message(en,Message,
		 ['The model must contain at least one function!'
		 ]),
		 message('ERROR','',Message,'syntax5').

property5 :- 	tocheck(syntax5),
		not(clause(arc(_,_),true)),
		compose_message(de,Message,
		['Das Modell muss mindestens einen Kontrollflusspfeil enthalten!'
		 ]),
		compose_message(en,Message,
		 ['The model must contain at least one control-flow arc!'
		 ]),
		 message('ERROR','',Message,'syntax5').

% Property 6 - Functions have exactly one incoming arc and exactly one outgoing arc.
property6(X) :- tocheck(syntax6),
		function(X),more_than_one_outgoing_arcs(X),
		compose_message(de,Message,
		['Die Funktion hat mehr als einen ausgehenden Kontrollflusspfeil!'
		 ]),
		compose_message(en,Message,
		 ['The function has more than one outgoing control-flow arc!'
		 ]),
		 message('ERROR',X,Message,'syntax6').

property6(X) :- tocheck(syntax6),
		function(X),no_outgoing_arcs(X),
		compose_message(de,Message,
		['Die Funktion hat keinen ausgehenden Kontrollflusspfeil!'
		 ]),
		compose_message(en,Message,
		 ['The function has no outgoing control-flow arcs!'
		 ]),
		 message('ERROR',X,Message,'syntax6').

property6(X) :- tocheck(syntax6),
		function(X),more_than_one_incoming_arcs(X),
		compose_message(de,Message,
		['Die Funktion hat mehr als einen eingehenden Kontrollflusspfeil!'
		 ]),
		compose_message(en,Message,
		 ['The function has more than one incoming control-flow arc!'
		 ]),
		 message('ERROR',X,Message,'syntax6').

property6(X) :- tocheck(syntax6),
		function(X),no_incoming_arcs(X),
		compose_message(de,Message,
		['Die Funktion hat keinen eingehenden Kontrollflusspfeil!'
		 ]),
		compose_message(en,Message,
		 ['The function has no incoming control-flow arcs!'
		 ]),
		 message('ERROR',X,Message,'syntax6').


% Property 7 - Events have at most one incoming and at most one outgoing arc.
% (note: This would allow events with no incoming and no outgoing arc, but this would be detected when verifying property 2)
property7(X) :- event(X),more_than_one_outgoing_arcs(X),
		compose_message(de,Message,
		['Das Ereignis hat mehr als einen ausgehenden Kontrollflusspfeil!'
		 ]),
		compose_message(en,Message,
		 ['The event has more than one outgoing control-flow arc!'
		 ]),
		 message('ERROR',X,Message,'syntax7').

property7(X) :- event(X),more_than_one_incoming_arcs(X),
		compose_message(de,Message,
		['Das Ereignis hat mehr als einen eingehenden Kontrollflusspfeil!'
		 ]),
		compose_message(en,Message,
		 ['The event has more than one incoming control-flow arc!'
		 ]),
		 message('ERROR',X,Message,'syntax7').

% Property 8 - Two kinds of connectors are allowed:
%               split connectors with exactly one incoming arc and at least two outgoing arcs
%               join connectors with at least two incoming arc and exactly one outgoing arc.
%               (note: splits with no incoming and no outgoing arcs are already detected by property2.)
property8(X) :- connector(X),more_than_one_outgoing_arcs(X),more_than_one_incoming_arcs(X),type(X,Type),
		compose_message(de,Message,
		['Der ',
		Type,
		'-Konnektor hat sowohl mehr als eine eingehende als auch mehr als eine ausgehende Kante!'
		 ]),
		compose_message(en,Message,
		 ['The ',
		 Type,
		 'connector has both more than one incoming and more than one outgoing arc!'
		 ]),
		 message('ERROR',X,Message,'syntax8').

% Property 9 - Cycles made up only of connectors are forbidden
property9 :-	path_containing_connectors_only(X,X,_),
		compose_message(de,Message,
		['Es gibt einen Zyklus, der nur aus Konnektoren besteht!'
		 ]),
		compose_message(en,Message,
		 ['There is a cycle between connectors!'
		 ]),
		 message('ERROR',X,Message,'syntax9').

% Property 10 - If an event has an outgoing arc, this arc connects the event (possibly via one or more connectors)
%              to a function.
property10(X,Y) :- event(X),successor(X,Y),event(Y),
		compose_message(de,Message,
		['Einem Ereignis muss eine Funktion folgen!'
		 ]),
		compose_message(en,Message,
		 ['An event should be followed by a function!'
		 ]),
		 message('WARNING',X,Message,'syntax10').

% Property 11- The outgoing arc of a function connects this function (possibly via one or more connectors)
%              to an event
property11(X,Y) :- function(X),successor(X,Y),function(Y),
		compose_message(de,Message,
		['Einer Funktion muss ein Ereignis folgen!'
		 ]),
		compose_message(en,Message,
		 ['A function should be followed by an event!'
		 ]),
		 message('WARNING',X,Message,'syntax11').


% Property 12 - If an event is followed by one or more connectors, none of these connectors is an XOR-split or OR-split
property12(X) :- event(X),successor(X,Y) , ( xorsplit(Y) ; orsplit(Y)),type(Y,Type),
		compose_message(de,Message,
		['Einem Ereignis darf kein ',
		Type,
		 '-Split folgen!'
		 ]),
		compose_message(en,Message,
		 ['An event should not be followed by an ',
		 Type,
		 '-split!'
		 ]),
		 message('WARNING',X,Message,'syntax12').

% Property 13a - There is at least one start event, i.e. an event which has no incoming arcs
property13a :- findall(X,startevent(X),L),
		L == [],
		compose_message(de,Message,
		['Das Modell enthaelt kein Startereignis!'
		 ]),
		compose_message(en,Message,
		 ['The model does not contain any start events!'
		 ]),
		 message('ERROR','',Message,'syntax13').

% Property 13b - There is at least one end event, i.e. an event which has no outgoing arcs.
property13b :-	findall(X,endevent(X),L),
		L == [],
		compose_message(de,Message,
		['Das Modell enthaelt kein Endereignis!'
		 ]),
		compose_message(en,Message,
		 ['The model does not contain any end events!'
		 ]),
		 message('ERROR','',Message,'syntax13').

% Property 14 - For every element X, there is a path from a start event to X element can be reached from a start event
adddirectsuccessors(OldSet,NewSet) :- findall(Y,(member(X,OldSet),arc(X,Y),not(ord_memberchk(Y,OldSet))),NewlyFound),
				list_to_ord_set(NewlyFound,NewlyFoundOrd),
				ord_union(OldSet,NewlyFoundOrd,NewSet),
				OldSet \= NewSet.

reachable_from(List,X) :- adddirectsuccessors(List,ListPlus),reachable_from(ListPlus,X).
reachable_from(List,List).


property14 :- 	startevent(_),	%not tested if there are no start events at all (see property13a)
		findall(X,(function(X);event(X);connector(X)),AllElements),list_to_ord_set(AllElements,AllElementsOrdered),
		findall(S,startevent(S),Startevents),
		list_to_ord_set(Startevents,StarteventsOrdered),
		reachable_from(StarteventsOrdered,ReachableElements),!,
		ord_subtract(AllElementsOrdered,ReachableElements,UnreachableElements),
		not(ord_empty(UnreachableElements)),
		compose_message(de,Message,
		['Das Element ist nicht von einem Startereignis aus erreichbar!'
		 ]),
		compose_message(en,Message,
		 ['The element cannot be reached from a start event!'
		 ]),
		findall(X,(member(X,UnreachableElements),message('ERROR',X,Message,'syntax14')),_).

% Property 15 - For every element X, there is a path from X to an end event
adddirectpredecessors(OldSet,NewSet) :- findall(Y,(member(X,OldSet),arc(Y,X),not(ord_memberchk(Y,OldSet))),NewlyFound),
				list_to_ord_set(NewlyFound,NewlyFoundOrd),
				ord_union(OldSet,NewlyFoundOrd,NewSet),
				OldSet \= NewSet.

% The elements in X can reach the elements in List
can_reach(List,X) :- adddirectpredecessors(List,ListPlus),can_reach(ListPlus,X).
can_reach(List,List).

property15 :- 	endevent(_),	%not tested if there are no start events at all (see property13a)
		findall(X,(function(X);event(X);connector(X)),AllElements),list_to_ord_set(AllElements,AllElementsOrdered),
		findall(S,endevent(S),Endevents),
		list_to_ord_set(Endevents,EndeventsOrdered),
		can_reach(EndeventsOrdered,PreceedingElements),!,
		ord_subtract(AllElementsOrdered,PreceedingElements,UnreachableElements),
		not(ord_empty(UnreachableElements)),
		compose_message(de,Message,
		['Von diesem Element aus ist kein Endereignis erreichbar!'
		 ]),
		compose_message(en,Message,
		 ['No end event can be reached from this event!'
		 ]),
		findall(X,(member(X,UnreachableElements),message('ERROR',X,Message,'syntax15')),_).


% Additional Properties:
% (Need to be checked only after importing models from dubious sources)
arc_property1 :- not(clause(arc(_,_),true)).
arc_property(X,Y) :- arc(X,Y), (not(element(X));not(element(Y))).



%===========================================================================================================
% SEMANTIKPRÜFUNGEN
%==========================================================================================================

%-----------------------------------------------------------------------------------------------------------
% [A] xor_to_and
% (X)OR-Split ist kombiniert mit AND-Join
% Wenn es "Seiteneinstiege" oder Transfers zwischen den beiden Pfaden gibt, wird nur eine Warnung ausgegeben,
% sonst eine Fehlermeldung
%-----------------------------------------------------------------------------------------------------------
mismatch_error(S,J) 		:- tocheck(xor_to_and),
				not(andsplit(S)),andjoin(J),
				match(S,J),type(S,TypeS),type(J,TypeJ),
				(
				(no_sidestep_match(S,J),Msg1DE = 'Unerlaubte Kombination ',Msg1EN = 'is not allowed.')
				 ;
				 (Msg1DE = 'Eventuell unerlaubte Kombination ',Msg1EN = 'is probably not allowed.')
				),
				compose_message(de,Message,
				[
				Msg1DE,
				TypeS,
				'-Split --> ',
				TypeJ,
				'-Join'
				]),
				compose_message(en,Message,
				[
				'The combination ',
				TypeS,
				'-split --> ',
				TypeJ,
				'-join',
				Msg1EN
				]),
				message('ERROR',J,Message,'xor_to_and'),!.

%-----------------------------------------------------------------------------------------------------------
% [B] and_to_xor
% AND- bwz. OR-Split ist kombiniert mit XOR-Join
%-----------------------------------------------------------------------------------------------------------
mismatch_error(S,J) 		:- tocheck(and_to_xor),
				not(xorsplit(S)),xorjoin(J),
				match(S,J),
				type(S,TypeS),type(J,TypeJ),
				compose_message(de,Message,
				['Unerlaubte Kombination ',
				TypeS,
				'-Split --> ',
				TypeJ,
				'-Join'
				]),
				compose_message(en,Message,
				['The combination ',
				TypeS,
				'-split --> ',
				TypeJ,
				'-join',
				'is not allowed.'
				]),
				message('ERROR',J,Message,'and_to_xor'),!.


%-----------------------------------------------------------------------------------------------------------
% [H] and_to_or
% AND- bzw. OR-Split ist kombiniert mit OR-Join
% Warnung, wenn J ein OR-Join ist und alle(!) S mit match(S,J) entweder XOR oder AND-Splits sind
%  not(match_with_true_upstream_entry(_,J,_))
% muss im Falle xorsplit(S) nicht gefordert werden, da das schon aus 
% not(multiple_startevents_before_join(J)) und  not(match_from_or(_,J)),not(match_from_and(_,J) folgt.
%-----------------------------------------------------------------------------------------------------------

mismatch_warning(S,J)		:- tocheck(and_to_or),
				   orjoin(J),
				   not(multiple_startevents_before_join(J)),
				   (((match(S,J),andsplit(S),not(cycle_entry(J)),
				    not(match_from_or(_,J)),
				    not(match_from_xor(_,J)),
				    not(match_from_and_with_exit(_,J))
				   )
				   ;
				   (xorsplit(S),
				    (match_without_entry(S,J);match_with_downstream_entry(S,J,_)),
				    not(match_from_or(_,J)),
				    not(match_from_and(_,J))
				   ))),
				   type(S,Type),
				compose_message(de,Message,
				['Der or-Join kann ersetzt werden durch einen Join vom Typ ',
				Type
				]),
				compose_message(en,Message,
				['The or-join can be substituted by an ',
				Type,
				'-join.'
				]),
   				message('WARNING',J,Message,'and_to_or'),!.


%-----------------------------------------------------------------------------------------------------------			
% [E] and_to_and_upstream_entry_xor
% In einen AND-Kontrollblock gibt es einen Upstream-Einsprung vom Typ XOR
%-----------------------------------------------------------------------------------------------------------
mismatch_error(S,J,E) 		:- tocheck(and_to_and_upstream_entry_xor),
				andconnector(S),andconnector(J),xorconnector(E),
				match_with_upstream_entry(S,J,E),
				(
				(no_sidestep_match(S,J),Msg1DE = '', Msg1EN = '', Msgtype='ERROR')
				 ;
				 (Msg1DE = ' moeglicherweise',Msg1EN = ' probably', Msgtype='WARNING')
				),
				compose_message(de,Message,
				['Der XOR-Einsprung in einen AND-Kontrollblock fuehrt',
				Msg1DE,
				' zu einen Fehler im AND-Kontrollblock'
				]),
				compose_message(en,Message,
				['The XOR-entry into the AND-control block leads',
				Msg1EN,
				' to an error in the AND-control block.'
				]),
				message(Msgtype,E,Message,'and_to_and_upstream_entry_xor'),!.


%-----------------------------------------------------------------------------------------------------------
% [F] Partial-Redo-Muster
%-----------------------------------------------------------------------------------------------------------

mismatch_error(S,J) 		:- tocheck(partial_redo),
				andconnector(S),orconnector(J),
				match_with_downstream_entry(S,J,E),
				compose_message(de,Message,
				['Pruefen Sie die Einspruenge in diesen AND-Kontrollblock. Kann es wirklich eintreten, dass ein Pfad (aber nicht alle) zu wiederholen ist?'
				]),
				compose_message(en,Message,
				['Double-check the entry into the AND-control block. Is it really the case that one (but not all) parallel paths have to be repeated?'
				]),
				message('WARNING',E,Message,'partial_redo'),!.


%-----------------------------------------------------------------------------------------------------------
% [G] and_to_and_downstream_entry
% In einen AND-Kontrollblock gibt es einen Downstream-Einsprung 
%-----------------------------------------------------------------------------------------------------------

mismatch_error(S,J,E) 		:- tocheck(and_to_and_downstream_entry),
				andconnector(S),andconnector(J),
				match_with_downstream_entry(S,J,E),
				(
				(no_sidestep_match(S,J),Msg1DE = '', Msg1EN = '', Msgtype='ERROR')
				 ;
				 Msg1DE = ' moeglicherweise', Msg1EN = ' probably', Msgtype='WARNING'
				),
				compose_message(de,Message,
				['Der Einsprung in einen AND-Kontrollblock bewirkt',
				Msg1DE,
				', dass am AND-Join nicht alle Eingaenge erreicht werden.'
				]),
				compose_message(en,Message,
				['The entry into an and-control block causes',
				Msg1EN,
				', that the AND-join cannot synchronize the paths.'
				]),
				message(Msgtype,E,Message,'and_to_and_downstream_entry'),!.




%-----------------------------------------------------------------------------------------------------------
% [I] or_starts_optional_execution
% Alternative "Etwas ausführen oder nichts tun" wird von Paar aus OR Split/Or-Join (statt XOR) gebildet
%-----------------------------------------------------------------------------------------------------------


style_error_alternative(X,Y) :- tocheck(or_starts_optional_execution),
				     orsplit(X),arc(X,Y),orjoin(Y), % OR-split is directly connected to OR-join
				     binarysplit(X),
				     every_path_from_node1_to_an_endevent_must_pass_node2(X,Y),
				     every_path_from_a_to_b_must_pass_c(X,X,Y),
				     compose_message(de,Message,
				     ['Der OR-Split sollte durch XOR ersetzt werden, um deutlich zu machen, dass die Abarbeitung optional erfolgt.'
				     ]),
				     compose_message(en,Message,
				     ['The or-split should be replaced by xor in order to show clearly that the execution of one branch is optional.'
				     ]),
				     message('WARNING',X,Message,'or_starts_optional_execution').


%-----------------------------------------------------------------------------------------------------------
% [Z] and_starts_optional_execution
%-----------------------------------------------------------------------------------------------------------

style_error_alternative(X,Y) :- tocheck(and_starts_optional_execution),
				     andsplit(X),arc(X,Y),(orjoin(Y);andjoin(Y)), % AND-split is directly connected to OR-join
				     binarysplit(X),
				     every_path_from_node1_to_an_endevent_must_pass_node2(X,Y),
				     every_path_from_a_to_b_must_pass_c(X,X,Y),
				     compose_message(de,Message,
				     ['Ein von diesem AND-Split ausgehender Pfeil ist nutzlos.'
				     ]),
				     compose_message(en,Message,
				     ['One of the outgoing arcs of this AND-split is useless.'
				     ]),
				     message('WARNING',X,Message,'and_starts_optional_execution').

%-----------------------------------------------------------------------------------------------------------
% [ZZ] Pfeil S->E ist nutzlos, da durch einen weiter davor stehenden AND-Split ohnehin der Kontrollfluss an
% E ankommt
%-----------------------------------------------------------------------------------------------------------

useless_arc(E,J) :- tocheck(and_starts_optional_execution),
			match_with_upstream_entry(S,J,E),andsplit(S),not(xorjoin(J)),not(xorjoin(E)),
			arc(S,E),arc(E,J),
			andsplit(A),path(A,S,Path1),list_to_ord_set(Path1,Path1Set),secondpath(A,E,_,Path1Set),
			not(match_with_exit(A,E,_)), % Beachte: S als AND-Split ist kein Exit
			compose_message(de,Message,
			['Ein von diesem AND-Split ausgehender Pfeil ist nutzlos.'
			]),
			compose_message(en,Message,
			['One of the outgoing arcs of this AND-split is useless.'
			]),
			message('WARNING',S,Message,'and_starts_optional_execution').

%-----------------------------------------------------------------------------------------------------------
% [K] and_to_and_upstream_entry_or
% In einen AND-Kontrollblock gibt es einen Einsprung vom Typ OR, direkt davor liegt ein (nutzloses) Startereignis
%-----------------------------------------------------------------------------------------------------------

mismatch_warning(S,J,E)		:- tocheck(and_to_and_upstream_entry_or),
				andconnector(S),andconnector(J),orconnector(E),
				match_with_upstream_entry(S,J,E),
				startevent(Start),arc(Start,E),
				compose_message(de,Message,
				['Das Startereignis scheint keinen Einfluss auf die Abarbeitungslogik zu haben. Ist der OR-Join dahinter korrekt?'
				]),
				compose_message(en,Message,
				['This start event has no effect on the execution logic. Is the OR-join after this event correct?'
				]),
				message('ERROR',Start,Message,'and_to_and_upstream_entry_or'),!.

				
%-----------------------------------------------------------------------------------------------------------
% [L] loop_exit_and				
% Ausstiegsknoten eines Zyklus ist kein XOR
%-----------------------------------------------------------------------------------------------------------

loop_exit_error(X) :- tocheck(loop_exit_and),
			(andsplit(X);orsplit(X)),cycle_exit(X),
			compose_message(de,Message,
			['Dieser Konnektor beendet eine Schleife und sollte daher vom Typ XOR sein.'
			]),
			compose_message(en,Message,
			['This split ends a loop and should be an XOR-split.'
			]),
			message('ERROR',X,Message,'loop_exit_and'),!.

%-----------------------------------------------------------------------------------------------------------
% [M] loop_entry_and
% Einstiegsknoten eines Zyklus ist ein AND-Join
%-----------------------------------------------------------------------------------------------------------
loop_entry_error(X) :- tocheck(loop_entry_and),
			andjoin(X),cycle_entry(X),
			compose_message(de,Message,
			['Dieser Konnektor startet eine Schleife und sollte daher vom Typ XOR sein.'
			]),
			compose_message(en,Message,
			['This split starts a loop and should be an XOR-split.'
			]),
			message('WARNING',X,Message,'loop_entry_and'),!.


%-----------------------------------------------------------------------------------------------------------
% [N] loop_entry_or
% Einstiegsknoten eines Zyklus ist ein OR-Join
%-----------------------------------------------------------------------------------------------------------

loop_entry_warning(X) :- tocheck(loop_entry_or),
			orjoin(X),cycle_entry(X),
			not(match_from_and(_,X)),not(match_from_or(_,X)),
			not(multiple_startevents_before_join(X)),
			compose_message(de,Message,
			['Dieser or-Join kann ersetzt werden durch einen Join vom Typ xor.'
			]),
			compose_message(en,Message,
			['This or-join can be replaced by an xor-join.'
			]),
			message('WARNING',X,Message,'loop_entry_or'),!.

%-----------------------------------------------------------------------------------------------------------
% [O] [OO] and_might_not_get_control
% An einem AND-Join kommen ggf. nicht an allen Eingängen Kontrollflüsse an, da zuvor ein (X)OR-Split den Kontrollfluss
% in eine andere Richtung lenken kann
% Trifft and_might_not_get_control(S,J) damit zusammen, dass J ein AND-entry in einen Zyklus ist,
% wird nur letzteres bemängelt.
% Wegen des ! am Ende und dem not(andsplit(S)) nicht zum direkten Aufruf geeignet.
%-----------------------------------------------------------------------------------------------------------



and_might_not_get_control(S,J) :- tocheck(and_might_not_get_control),
				not(andsplit(S)),
				andjoin(J),
				path(S,J,_),
				arc(S,SSucc),SSucc \== J,
				not(path(SSucc,J,_)),
				arc(JPred,J), JPred \== S,
				not(path(S,JPred,_)),
				not(cycle_entry(J)),
			  	not((andsplit(Anch),common_ancestor(S,J,Anch),not(match(Anch,J)))),
				compose_message(de,Message,
				['Evtl. werden nicht alle Eingaenge des and-Joins erreicht, da zuvor ein (x)or-Split den Kontrollfluss in eine andere Richtung leitet.'
				]),
				compose_message(en,Message,
				['The and-split might not get control because of a preceding (x)or-split.'
				]),
				message('ERROR',J,Message,'and_might_not_get_control'),!.

%-----------------------------------------------------------------------------------------------------------
% [P] startevents_blockieren_nach_split
% deutet darauf hin, dass an den Startereignissen ein beabsichtigter Deadlock modelliert wurde
%-----------------------------------------------------------------------------------------------------------

style_error_startevents :- 	tocheck(startevents_blockieren_nach_split),
				split(X),
				arc(X,A1),arc(X,A2),A1 @< A2, % dann ist insbes. A1\==A2,
				andjoin(A1),andjoin(A2),
				arc(S1,A1),arc(S2,A2),
				startevent(S1),startevent(S2),
				compose_message(de,Message,
				['Einer der folgenden AND-Joins kann moeglicherweise blockieren, wenn das entsprechende Startereignis nicht eintritt. Ggf. laesst sich dies besser modellieren.'
				]),
				compose_message(en,Message,
				['Looks like one of the AND-joins following to this split are expected to block. Consider alternative modelling.'
				]),
				message('WARNING',X,Message,'startevents_blockieren_nach_split').

%-----------------------------------------------------------------------------------------------------------
% [Q] startevents_andjoin
% deutet darauf hin, dass an den Startereignissen ein beabsichtigter Deadlock modelliert wurde
%-----------------------------------------------------------------------------------------------------------

style_error_startevents :-	tocheck(startevents_andjoin),
				andsplit(A1),arc(A1,A2),andjoin(A2),
				arc(X,A2),startevent(X),
				compose_message(de,Message,
				['Tritt dieses Startereignis nicht ein, so kann auch der dahinterliegende and-Join nicht durchlaufen werden. Kann man das besser modellieren?'
				]),
				compose_message(en,Message,
				['If this start event does not occur, the succeeding and-join blocks. Consider alternative modelling.'
				]),
				message('WARNING',X,Message,'startevents_andjoin').



%-----------------------------------------------------------------------------------------------------------
% [X] delta_warning 
% Der OR-Split kann durch einen XOR-Split ersetzt werden
%-----------------------------------------------------------------------------------------------------------
delta_warning :- tocheck(delta_warning),
		delta(S,From,_),andsplit(S),orsplit(From),
		compose_message(de,Message,
		['Dieser OR-Split kann ersetzt werden durch einen Split vom Typ XOR.'
		]),
		compose_message(en,Message,
		['This OR-split can be replaced by an XOR-split.'
		]),
		message('WARNING',From,Message,'delta_warning').



%-----------------------------------------------------------------------------------------------------------
% [W] nothing_to_do
% Es ist möglich, von einem Startereignis aus ein Endereignis zu erreichen, ohne je eine Funktion auszuführen
%-----------------------------------------------------------------------------------------------------------
nothing_to_do(Start,End) :- tocheck(nothing_to_do),
			path_without_functions(Start,End,_),
			compose_message(de,Message,
			['Dieses Endereignis kann erreicht werden, ohne dass jemals eine Funktion ausgefuehrt wurde.'
			]),
			compose_message(en,Message,
			['This end event can be reached without ever executing a function.'
			]),
			message('WARNING',End,Message,'nothing_to_do').


%-----------------------------------------------------------------------------------------------------------
% [S] ancestor_mismatch
% Modellierungsstil: Zwei Joins J1 und J2 haben zwei gemeinsame Vorfahren S1 und S2,
% jedoch sind die Typen von S1 und S2 verschieden.
% Beispiel: type(S1)=and und type(S2)=xor
% Missverständnis: Werden die Pfade zu J1 und J2 nun immer gemeinsam oder alternativ bearbeitet?
% Ist aber nicht notwendig "harter" Fehler!
%-----------------------------------------------------------------------------------------------------------

style_ancestor_mismatch(J1,J2) :- tocheck(ancestor_mismatch),
		join(J1),join(J2),J1 @< J2, % o.B.d.A., insbesondere gilt J1 \== J2
		common_ancestor(J1,J2,S1),split(S2),S1 @< S2, % dann gilt insbesondere S1 \== S2
		not(have_same_type(S1,S2)),
		common_ancestor(J1,J2,S2),
		not(path(J1,S1,_)),not(path(J1,S2,_)),not(path(J2,S1,_)),not(path(J2,S2,_)),
		compose_message(de,Message,
		['Sehr unuebersichtliche Modellierung! Es ist schwer zu erkennen, ob die beiden markierten Joins gleichzeitig oder alternativ erreicht werden.'
		]),
		compose_message(en,Message,
		['Very confusing modelling! It is difficult to see whether the marked joins will be reached alternatively or in parallel.'
		]),
		message('WARNING',J1,Message,'ancestor_mismatch'),
		message('WARNING',J2,Message,'ancestor_mismatch'),!.


%-----------------------------------------------------------------------------------------------------------
% [C] xor_without_consequences
% In einem Block zwischen XOR-Split und XOR-Join stehen nur Ereignisse
%-----------------------------------------------------------------------------------------------------------
xor_without_consequences :- tocheck(xor_without_consequences),
	xorsplit(S),
	arc(S,E1),event(E1),
	arc(E1,J),xorjoin(J),
	arc(S,E2),event(E2),E1 @< E2,
	arc(E2,J),
	findall(X1,arc(S,X1),Li1), delete(Li1,J,Li1_),sort(Li1_,Split_out),
	findall(X2,arc(X2,J),Li2),delete(Li2,S,Li2_),sort(Li2_,Join_in),
        Split_out == Join_in,
	compose_message(de,Message,
	['Es ist keine Reaktion auf die Ereignisse nach diesem XOR-Split modelliert. Ist es wirklich unerheblich, welches dieser Ereignisse eintritt?'
	]),
	compose_message(en,Message,
	['No reaction on the events after this XOR-split is modelled. Does it really not matter which of these events occurs?'
	]),
	message('WARNING',S,Message,'xor_without_consequences'),!.

analyze0 :- vprintln('###01###'),startevent(Start),endevent(End),nothing_to_do(Start,End).
analyze0 :- vprintln('###02###'),xor_without_consequences.
analyze0 :- vprintln('###03###'),style_error_alternative(_,_).
analyze :- vprintln('###1###'),match(S,J),
				(mismatch_error(S,J)
				;mismatch_warning(S,J)
				;
				 (join(E),(mismatch_error(S,J,E);mismatch_warning(S,J,E)))
				).

analyze :- vprintln('###2###'),(xorsplit(S);orsplit(S)),andjoin(J),and_might_not_get_control(S,J).
analyze :- vprintln('###3###'),split(X),loop_exit_error(X).
analyze :- vprintln('###4###'),join(X),(loop_entry_error(X);loop_entry_warning(X)).
analyze :- vprintln('###5###'),useless_arc(_,_).
analyze :- vprintln('###6###'),style_error_startevents.
analyze :- vprintln('###7###'),join(J1),join(J2),J1 @< J2,style_ancestor_mismatch(J1,J2).
analyze :- vprintln('###8###'),delta_warning.

%===========================================================================================================
% REDUKTIONSREGELN
%===========================================================================================================
:- dynamic region/1.             % per Graphreduktion als korrekt erkannter Modellteil
% Es kann sinnvoll werden, Regions zu cachen.
% Reduktionsregeln für Sequenzen:
% fasst Sequenzen von Events und Funktionen zu einer einzigen Region zusammen:

region(X) :- event(X).
region(X) :- function(X).

forget(Me) :- % vprint('Vergiss: '),vprintln(Me),
	((arc(In,Me),retract(arc(In,Me)),fail);true), % Alle Arcs von und zu diesem Element vergessen
	((arc(Me,Out),retract(arc(Me,Out)),fail);true),
	(retract(region(Me));retract(function(Me));retract(event(Me));
	 retract(xor(Me));retract(or(Me));retract(and(Me));retract(processinterface(Me))).
	 
reduce_processinterface :- pi(P),event(E),
		(
			(arc(P,E),no_incoming_arcs(P))
		;
			(arc(E,P),no_outgoing_arcs(P))
		),
		vprint('Reduce process interface '),vprintln(P),
		forget(P).


reduce_sequence :- region(M1), arc(M1,M2), region(M2),
           arc(In,M1),arc(M2,Out),                                               % findet folglich keine Start- und Endereignisse
           atom_concat(M1,M2,S),assert(region(S)),
           vprint('Reduce Sequence: '),vprintln(S),
           assert(arc(In,S)),
           assert(arc(S,Out)),
           retract(arc(M1,M2)),
	forget(M1),forget(M2).


% Reduktionsregel für korrekte Blöcke

split_join_block(Split,Join,Split_out) :-
		  findall(X1,arc(Split,X1),Li1), delete(Li1,Join,Li1_),sort(Li1_,Split_out), % Das delete ist notwenig, da ein Pfeil direkt vom Split zum Join zeigen kann.
		  not(list_contains_connectors(Split_out)), % Dann können nur Regions folgen.
                  findall(X2,arc(X2,Join),Li2),delete(Li2,Split,Li2_),sort(Li2_,Join_in),
                  Split_out == Join_in.

list_contains_connectors(List) :- (member(C,List),connector(C)).

reduce_wellstructured_split_join_block :-   ((xorsplit(Split),xorjoin(Join));
		 (orsplit(Split),orjoin(Join));
		 (andsplit(Split),andjoin(Join))),
		split_join_block(Split,Join,Regions),
           	arc(In,Split),arc(Join,Out),
           	type(Split,Type),
		concat_atom([begin_,Type,Split,Join,end_,Type],S),
          	assert(region(S)),vprint('Reduce '),vprint(Type),vprint('-block: '),vprintln(S),
           	assert(arc(In,S)),
	   	assert(arc(S,Out)),
		forget(Split),forget(Join),
                ((member(Member,Regions),forget(Member),fail);true).

reduce_correct_split_join_block :-  orjoin(Join),(andsplit(Split);xorsplit(Split)),
		split_join_block(Split,Join,Regions),
           	arc(In,Split),arc(Join,Out),
           	type(Split,Type),
		assertz(unstructured),
		((tocheck(and_to_or),
			type(Split,Type),
			compose_message(de,Message,
			['Der or-Join kann ersetzt werden durch einen Join vom Typ ',
			Type
			]),
			compose_message(en,Message,
			['The or-join can be substituted by an ',
			Type,
			'-join.'
			]),
			message('WARNING',Join,Message,'and_to_or')
			);
			true
		),
		concat_atom([begin_,Type,Split,Join,end_,Type],S),
          	assert(region(S)),vprint('Reduce '),vprint(Type),vprint('->OR-block: '),vprintln(S),
           	assert(arc(In,S)),
	   	assert(arc(S,Out)),
		forget(Split),forget(Join),
                ((member(Member,Regions),forget(Member),fail);true).

% Mindestens drei verschiedene Vorkommen Split -> Region -> Join (unabh. vom Typ der Splits und Joins)
reduce_big_split_join_block :- split(Split),
		arc(Split,R1),region(R1),arc(Split,R2),region(R2),R2 \== R1, arc(Split,R3), region(R3), R3 \== R1,R3 \== R2,
		arc(R1,Join),join(Join),arc(R2,Join),arc(R3,Join),
		forget(R1),
		vprint('Between split '),vprint(Split),vprint(' and join '),vprint(Join),
		vprintln(' are more than two arcs split->node->join; We delete one of them!').
		

% Reduktionsregel für Iterationen (nur für Iterationen, bei denen zwischen XOR-Split und -Join
% höchstens ein Event, eine Funktion oder eine Region liegt

reduce_wellstructured_iteration :- xorjoin(X1),xorsplit(X2),binaryjoin(X1),binarysplit(X2),
		(arc(X1,X2);(arc(X1,R1),arc(R1,X2),not(connector(R1)))),
		(arc(X2,X1);(arc(X2,R2),arc(R2,X1),not(connector(R2)))),
		arc(In,X1),In \== X2, In \== R2, arc(X2,Out), Out \== X1, Out \== R2,
		concat_atom([begin_iteration_,X1,X2,end_iteration_],S),
          	assert(region(S)),vprint('Reduce iteration block: '),vprintln(S),
		forget(X1),forget(X2),
		((nonvar(R1),forget(R1));true),
		((nonvar(R2),forget(R2));true),
		assert(arc(In,S)),assert(arc(S,Out)).

% Seiteneffekt: assertz(unstructured)

reduce_correct_iteration :- orjoin(X1),xorsplit(X2),binaryjoin(X1),binarysplit(X2),
		(arc(X1,X2);(arc(X1,R1),arc(R1,X2),not(connector(R1)))),
		(arc(X2,X1);(arc(X2,R2),arc(R2,X1),not(connector(R2)))),
		arc(In,X1),In \== X2, In \== R2, arc(X2,Out), Out \== X1, Out \== R2,
		concat_atom([begin_iteration_,X1,X2,end_iteration_],S),
% tocheck(loop_entry_and)
		print('[N] consider to replace the loop entry '),print(X2),println(' by an XOR-join'),
		assertz(unstructured),
          	assert(region(S)),vprint('Reduce iteration block: '),vprintln(S),
		forget(X1),forget(X2),
		((nonvar(R1),forget(R1));true),
		((nonvar(R2),forget(R2));true),
		assert(arc(In,S)),assert(arc(S,Out)).		


merge_startevent_region_join :- startevent(A),arc(A,R),arc(R,Join),join(Join),region(R),
				atom_concat(A,R,AR),
				forget(A),forget(R),assert(event(AR)),assert(arc(AR,Join)),
				vprint('Merging start event '),vprint(A),vprint(' with region '),
				vprintln(R).

           
merge_startevents :- startevent(A),arc(A,Join),join(Join),arc(B,Join),A\==B,startevent(B),!,
                                type(Join,Type),
                                vprint('Merge start-events '),vprint(A),vprint(' and '),vprintln(B),
				concat_atom([merged_startevent_,Type,A,B],S),
                                assert(event(S)),
                                forget(A),forget(B),
                                % Wenn am Join nix weiter ankommt, kann er verschwinden:
				(
				 (not(arc(_,Join)),arc(Join,After),forget(Join),assert(arc(S,After)))
				;
				 assert(arc(S,Join))
				).

merge_split_region_endevent :- endevent(A),arc(R,A),arc(Split,R),split(Split),region(R),
				atom_concat(R,A,RA),
				forget(A),forget(R),assert(event(RA)),assert(arc(Split,RA)),
				vprint('Merging region '),vprint(R),vprint(' with end event '),
				vprintln(A).

merge_endevents :- endevent(A),arc(Split,A),split(Split),arc(Split,B),A\==B,endevent(B),!,
                                type(Split,Type),
                                vprint('Merge end-events '),vprint(A),vprint(' and '),vprintln(B),
				concat_atom([merged_endevent_,Type,A,B],S),
                                assert(event(S)),
                                forget(A),forget(B),
				(
				 (not(arc(Split,_)),arc(Before,Split),forget(Split),assert(arc(Before,S)))
				;
				 assert(arc(Split,S))
				).

delete_and_endevent :- andsplit(S),arc(S,End),endevent(End),not(path(S,S,_)),
		arc(In,S),arc(S,Out),Out \== End,
		vprint('Delete endevent '),vprint(End),vprint(' after AND-split '),vprintln(S),
		((binarysplit(S),forget(S),assert(arc(In,Out)));true), %Binary Splits vergessen, sonst nur das Endevent vergessen
		forget(End).


reduce_homogeneous :- split(S),join(J),sese(S,J,Between),have_same_type(S,J),
		% In Between dürfen dann nur Konnektoren des selben Typs liegen:
		not((connector(C),not(have_same_type(C,S)),ord_memberchk(C,Between))),
		% Ist der gemeinsame Typ AND- oder OR, sind außerdem Zyklen verboten:
		(xorsplit(S)
		 ; 
		(not(( ord_memberchk(Join,Between),join(Join),path(Join,Join,_)  )))
		),
		arc(In,S),arc(J,Out),
		vprint('Reduce homogeneous block between split '),vprint(S),vprint(' and join '),vprintln(J),
		forget(S),forget(J),
		((member(Member,Between),forget(Member),fail);true), %ord_memberchk does not work here!
		assert(arc(In,Out)).
		


% Wende Reduktionsregeln wiederholt an.
% Wohlgeformte Modelle werden reduziert zu: Startevent -> Region -> Endevent
% Reduktionsregeln liefern true, wenn Reduzierung stattfand, sonst false

re :- (		(reduce_processinterface,!);
      		(reduce_sequence,!);
	  		(reduce_wellstructured_split_join_block,!);
          	(reduce_correct_split_join_block,!);
          	(merge_startevent_region_join,!);
	  		(merge_split_region_endevent,!);
          	(merge_startevents,!);
          	(merge_endevents,!);
	  		(reduce_wellstructured_iteration,!);
	  		(reduce_correct_iteration,!);
	 		(reduce_big_split_join_block,!);
	  		(delete_and_endevent,!);
	  		(reduce_homogeneous,!)
       ),
        re;reduced.



reduced :-  findall([A,B],arc(A,B),L),not(nth1(3,L,_)),              % Es gibt nur 2 Arcs
                    startevent(S),region(R),endevent(E),arc(S,R),arc(R,E).



%===========================================================================================================
% PRÜFUNGEN DEUTSCHSPRACHIGER BESCHRIFTUNGEN
%===========================================================================================================

%TOFIX: problem4
%TOFIX: trinity('b=a','a<b','b<a')
%TODO: 'a und b stimmen [miteinander] überein' <---> a = b
% Todo: Prüfe, ob @< mit =/</> korrespondiert


% vor oder nach AND bzw. OR stehen Ereignisse, die sich ausschließen
analyze_text :- vprintln('...Text1...'),xandnotx.
% Vor bzw. nach einem Konnektor folgen zwei logisch identische Ereignisse
analyze_text :- vprintln('...Text2...'),xxorx.
% Vor oder nach einem XOR steht ein Ereignis, seine Negation sowie noch ein weiteres Ereignis
analyze_text :- vprintln('...Text3...'),tertium_non_datur.
% Warnung, wenn der Fall "Gleichheit" / "bleibt unverändert" nicht beachtet wurde
analyze_text :- vprintln('...Text4...'),greaterlessandnoequal.
% contradicting_startevents
analyze_text :- vprintln('...Text5...'),contradicting_startevents.
% contradicting_endevents
analyze_text :- vprintln('...Text6...'),contradicting_endevents.
% tertium_non_datur_startevents
analyze_text :- vprintln('...Text7...'),tertium_non_datur_startevents.
% contradicting_startevents
analyze_text :- vprintln('...Text8...'),tertium_non_datur_endevents.
% not_xor_after_yesno_question
analyze_text :- vprintln('...Text9...'),not_xor_after_yesno_question.
% trivialereignis
analyze_text :- vprintln('...Text10..'),trivialereignis.
% same_startevents und same_endevents noch nicht eingebunden.

% Ein Ereignis ist mit trivialem Text wie "ok" beschriftet
trivialereignis :- tocheck(trivialereignis),
	event(E),elementname(E,NameE),trivialereignis(NameE),
	compose_message(de,Message,
	['Die Beschriftung dieses Ereignisses sagt zu wenig aus.'
	]),
	compose_message(en,Message,
	['--- not yet implemented ---'
	]),
	message('WARNING',E,Message,'trivialereignis').

% vor oder nach AND bzw. OR stehen Ereignisse, die sich ausschließen
% Dabei wird im trinity-Fall noch nicht der Fall A=B / A<B geprüft
xandnotx :-tocheck(xandnotx),
	connector_and_two_events(C,Type,_,_,NameE1,NameE2,SplitOrJoin),
	not(xorconnector(C)),
	(negation(NameE1,NameE2);trinity_minus_equality(NameE1,NameE2)),
	(
	 (SplitOrJoin==split, FolgenOderVoranzugehen = '<< zu folgen')
	;
	 (SplitOrJoin==join, FolgenOderVoranzugehen = '<< voranzugehen')
	),
	compose_message(de,Message,
	['Dem ',
	Type,
	'-',
	SplitOrJoin,
	' scheinen zwei Ereignisse >>',
	NameE1,
	'<<  und  >>',
	NameE2,
	FolgenOderVoranzugehen,
	', die sich ausschliessen.'
	]),
	compose_message(en,Message,
	['--- not yet implemented ---'
	]),
	message('WARNING',C,Message,'xandnotx').




% Vor bzw. nach einem Konnektor folgen zwei logisch identische Ereignisse
xxorx :- tocheck(xxorx),
	connector_and_two_events(C,Type,_,_,NameE1,NameE2,SplitOrJoin),
	equivalent(NameE1,NameE2),not(trivialereignis(NameE1)),not(trivialereignis(NameE2)),
	(
	 (SplitOrJoin==split, FolgenOderVoranzugehen = '<< zu folgen')
	;
	 (SplitOrJoin==join, FolgenOderVoranzugehen = '<< voranzugehen')
	),
	compose_message(de,Message,
	['Dem ',
	Type,
	'-',
	SplitOrJoin,
	' scheinen zwei Ereignisse >>',
	NameE1,
	'<<  und  >>',
	NameE2,
	FolgenOderVoranzugehen,
	', die den selben Sachverhalt beschreiben. Kann man das einfacher darstellen?'
	]),
	compose_message(en,Message,
	['--- not yet implemented ---'
	]),
	message('WARNING',C,Message,'xxorx').




% Vor oder nach einem XOR steht ein Ereignis, seine Negation sowie noch ein weiteres Ereignis	
tertium_non_datur :- tocheck(tertium_non_datur),
	connector_and_two_events(C,xor,E1,E2,NameE1,NameE2,SplitOrJoin),
	negation(NameE1,NameE2),
	((split(C),successor(C,E3),event(E3),E3\==E1,E3\==E2)
	 ;
	 (join(C),successor(E3,C),event(E3),E3\==E1,E3\==E2)
	),
	elementname(E3,NameE3),canonical([NameE3,true],[NameE3NF,true]),
	not(restricted_proposition(NameE3NF)),
	(
	 (SplitOrJoin==split, VorOderNach = ' nach dem XOR-Split')
	;
	 (SplitOrJoin==join, VorOderNach = ' vor dem XOR-Join')
	),
	compose_message(de,Message,
	['Tritt wirklich genau eines der folgenden Ereignisse',
	VorOderNach,
	' ein?: >>',
	NameE1,
	'<<  und  >>',
	NameE2,
	'<<  und  >>',
	NameE3,
	'<<'
	]),
	compose_message(en,Message,
	['--- not yet implemented ---'
	]),
	message('WARNING',C,Message,'tertium_non_datur').

% Warnung, wenn der Fall "Gleichheit" / "bleibt unverändert" nicht beachtet wurde
greaterlessandnoequal :- tocheck(greaterlessandnoequal),
	connector_and_two_events(C,Type,_,_,NameE1,NameE2,SplitOrJoin),
	trinity_minus_equality(NameE1,NameE2),
	not(equality_included(C,NameE1,NameE2)),
	(
	 (SplitOrJoin==split, FolgenOderVoranzugehen ='<< zu folgen')
	;
	 (SplitOrJoin==join, FolgenOderVoranzugehen ='<< voranzugehen')
	),
	compose_message(de,Message,
	['Dem',
	Type,
	'-',
	SplitOrJoin,
	'scheinen zwei Ereignisse >>',
	NameE1,
	'<<  und  >>',
	NameE2,
	FolgenOderVoranzugehen,
	' , zu denen das dritte Ereignis (Gleichheit / gleich bleiben) nicht modelliert wurde. Ist das korrekt?'
	]),
	compose_message(en,Message,
	['--- not yet implemented ---'
	]),

	message('WARNING',C,Message,'greaterlessandnoequal').


contradicting_startevents :- tocheck(contradicting_startevents),
			startevent(E1),startevent(E2),E1 @< E2,
			elementname(E1,NameE1),
			elementname(E2,NameE2),
			negation(NameE1,NameE2),
			(andjoin(J);orjoin(J)),
			not((arc(E1,J),arc(E2,J))), % denn in diesen Fall wird schon an anderer Stelle ein Fehler gemeldet
			path(E1,J,Path1),
			list_to_ord_set(Path1,Path1Set),
			secondpath(E2,J,_,Path1Set),
			type(J,Type),
			compose_message(de,Message,
			['Die einander widersprechenden Startereignisse <<',
			NameE1,
			'>> und <<',
			NameE2,
			'<< werden an einem ',
			Type,
			'-Join zusammengefuehrt. Ist das korrekt?'
			]),
			compose_message(en,Message,
			['--- not yet implemented ---'
			]),
			message('WARNING',J,Message,'contradicting_startevents').


same_startevents :- tocheck(same_startevents),
			startevent(E1),startevent(E2),E1 @< E2,
			elementname(E1,NameE1),
			elementname(E2,NameE2),
			equivalent(NameE1,NameE2),
			join(J),
			not((arc(E1,J),arc(E2,J))), % denn in diesen Fall wird schon an anderer Stelle ein Fehler gemeldet
			path(E1,J,Path1),
			list_to_ord_set(Path1,Path1Set),
			secondpath(E2,J,_,Path1Set),
			type(J,Type),
			compose_message(de,Message,
			['Die inhaltsgleichen Startereignisse <<',
			NameE1,
			'>> und <<',
			NameE2,
			'<< werden an einem ',
			Type,
			'-Join zusammengefuehrt. Ist das korrekt?'
			]),
			compose_message(en,Message,
			['--- not yet implemented ---'
			]),
			message('WARNING',J,Message,'same_startevents').

tertium_non_datur_startevents :- tocheck(tertium_non_datur_startevents),
			startevent(E1),startevent(E2),E1 @< E2,
			elementname(E1,NameE1),
			elementname(E2,NameE2),
			negation(NameE1,NameE2),
			xorjoin(J),
			not((arc(E1,J),arc(E2,J))),  % denn in diesen Fall wird schon an anderer Stelle ein Fehler gemeldet
			path(E1,J,Path1),
			list_to_ord_set(Path1,Path1Set),
			secondpath(E2,J,Path2,Path1Set),
			startevent(E3), E3 \== E1, E3 \== E2,
			list_to_ord_set(Path2,Path2Set),
			ord_union(Path1Set,Path2Set,Path1and2Set),
			secondpath(E3,J,_,Path1and2Set),
			elementname(E3,NameE3),canonical([NameE3,true],[NameE3NF,true]),
			not(restricted_proposition(NameE3NF)),
			compose_message(de,Message,
			['Tritt wirklich genau eines der folgenden Startereignisse ein?: >>',
			NameE1,
			'<< und  >>',
			NameE2,
			'<< und  >>',
			NameE3,
			'<<'
			]),
			compose_message(en,Message,
			['--- not yet implemented ---'
			]),
			message('WARNING',J,Message,'tertium_non_datur_startevents').

contradicting_endevents :-tocheck(contradicting_endevents),
			endevent(E1),endevent(E2),E1 @< E2,
			elementname(E1,NameE1),
			elementname(E2,NameE2),
			negation(NameE1,NameE2),
			(andsplit(S);orsplit(S)),
			not((arc(S,E1),arc(S,E2))),  % denn in diesen Fall wird schon an anderer Stelle ein Fehler gemeldet
			path(S,E1,Path1),
			list_to_ord_set(Path1,Path1Set),
			secondpath(S,E2,_,Path1Set),
			type(S,Type),
			compose_message(de,Message,
			['Die einander widersprechenden Endereignisse <<',
			NameE1,
			'>> und <<',
			NameE2,
			'<< treten gemeinsam nach einem ',
			Type,
			'-Split auf. Ist das korrekt?'
			]),
			compose_message(en,Message,
			['--- not yet implemented ---'
			]),
			message('WARNING',S,Message,'contradicting_endevents').

same_endevents :-tocheck(same_endevents),
			endevent(E1),endevent(E2),E1 @< E2,
			elementname(E1,NameE1),
			elementname(E2,NameE2),
			equivalent(NameE1,NameE2),
			(andsplit(S);orsplit(S)),
			not((arc(S,E1),arc(S,E2))),  % denn in diesen Fall wird schon an anderer Stelle ein Fehler gemeldet
			path(S,E1,Path1),
			list_to_ord_set(Path1,Path1Set),
			secondpath(S,E2,_,Path1Set),
			type(S,Type),
			compose_message(de,Message,
			['Die inhaltsgleichen Endereignisse <<',
			NameE1,
			'>> und <<',
			NameE2,
			'<< treten gemeinsam nach einem ',
			Type,
			'-Split auf. Ist das korrekt?'
			]),
			compose_message(en,Message,
			['--- not yet implemented ---'
			]),
			message('WARNING',S,Message,'same_endevents').

tertium_non_datur_endevents :- tocheck(tertium_non_datur_endevents),
			endevent(E1),endevent(E2),E1 @< E2,
			elementname(E1,NameE1),
			elementname(E2,NameE2),
			negation(NameE1,NameE2),
			xorjoin(J),
			not((arc(S,E1),arc(S,E2))),  % denn in diesen Fall wird schon an anderer Stelle ein Fehler gemeldet
			path(J,E1,Path1),
			list_to_ord_set(Path1,Path1Set),
			secondpath(J,E2,Path2,Path1Set),
			endevent(E3), E3 \== E1, E3 \== E2,
			list_to_ord_set(Path2,Path2Set),
			ord_union(Path1Set,Path2Set,Path1and2Set),
			secondpath(J,E3,_,Path1and2Set),
			elementname(E3,NameE3),canonical([NameE3,true],[NameE3NF,true]),
			not(restricted_proposition(NameE3NF)),
			compose_message(de,Message,
			['Tritt wirklich genau eines der folgenden Endereignisse ein?: >>',
			NameE1,
			'<< und  >>',
			NameE2,
			'<< und  >>',
			NameE3,
			'<<'
			]),
			compose_message(en,Message,
			['--- not yet implemented ---'
			]),
			message('WARNING',J,Message,'tertium_non_datur_endevents').

not_xor_after_yesno_question :- tocheck(not_xor_after_yesno_question),
			function(F),elementname(F,FName),yesno_question(FName),
			arc(F,S),(orsplit(S);andsplit(S)),
			elementname(F,NameF),!,
			compose_message(de,Message,
			[
			'Auf die Entscheidung >>> ',
			NameF,
			' <<< sollte ein xor-Split folgen.'
			]),
			compose_message(en,Message,
			['--- not yet implemented ---'
			]),
			message('WARNING',S,Message,'not_xor_after_yesno_question'),!.

restricted_proposition(X) :- restriction(R),sub_string(X,_,_,_,R).

yesno_question(X) :- sub_string(X,_,_,_,', ob ').
yesno_question(X) :- sub_string(X,_,_,0,'?'),
		     not((starts_constituent_question(W),sub_string(X,0,_,_,W))),
		     not((in_constituent_questiontion(V),sub_string(X,_,_,_,V))).
starts_constituent_question('warum').
starts_constituent_question('Warum').
starts_constituent_question('wieso').
starts_constituent_question('Wieso').
starts_constituent_question('weshalb').
starts_constituent_question('Weshalb').
starts_constituent_question('weswegen').
starts_constituent_question('Weswegen').
starts_constituent_question('was').
starts_constituent_question('Was').
starts_constituent_questiontion('wofür').
starts_constituent_questiontion('Wofür').
starts_constituent_questiontion('wofuer').
starts_constituent_questiontion('Wofuer').
starts_constituent_questiontiond('wozu').
starts_constituent_questiontion('Wozu').
starts_constituent_question('wer').
starts_constituent_question('Wer').
starts_constituent_question('wie').
starts_constituent_question('Wie').
starts_constituent_question('wem').
starts_constituent_question('Wem').
starts_constituent_question('wessen').
starts_constituent_question('Wessen').
%
in_constituent_questiontion('welche').
in_constituent_questiontion('Welche').

% ============================================================================================
% Grundlegende Prädikate für die Analyse der Modellbeschriftung
% ============================================================================================
equality_included(C,NameE1,NameE2) :-   	((split(C),arc(C,E3))
					      ;
					       (join(C),arc(E3,C))),
						elementname(E3,NameE3),
						trinity(NameE3,NameE1,NameE2).
						
% X wird von Y gefolgt (direkt oder über einige Splits)
% Muss auch für syntaktisch falsche Modelle (mit einem nur aus Konnektoren bestehenden Zyklus) funktionieren!
successor_via_splits(X,Y) :- arc(X,Y),X\==Y.
successor_via_splits(X,Y) :- arc(X,C),split(C),not(path_containing_connectors_only(X,X,_)),successor_via_splits(C,Y).

% X wird von Y gefolgt (direkt oder über einige Joins)
% Muss auch für syntaktisch falsche Modelle (mit einem nur aus Konnektoren bestehenden Zyklus) funktionieren!
successor_via_joins(X,Y) :- arc(X,Y),X\==Y.
successor_via_joins(X,Y) :- arc(X,C),join(C),not(path_containing_connectors_only(X,X,_)),successor_via_joins(C,Y).


% Ein Split führt zu zwei Ereignissen, und es ist der den Ereignissen "nächste" Split mit dieser Eigenschaft.
split_and_two_events(C,Type,E1,E2,NameE1,NameE2) :-
			split(C),type(C,Type),
			successor_via_splits(C,E1),event(E1),
			successor_via_splits(C,E2),event(E2),
			E1 @< E2,
			not((
				split(MoreDominatingSplit),
				MoreDominatingSplit\==C,
				successor_via_splits(MoreDominatingSplit,E1),
				successor_via_splits(MoreDominatingSplit,E2),
				every_path_from_a_to_b_must_pass_c(C,E1,MoreDominatingSplit),
				every_path_from_a_to_b_must_pass_c(C,E2,MoreDominatingSplit)
				)),
			elementname(E1,NameE1),
			elementname(E2,NameE2).

% Analog für Joins:
join_and_two_events(C,Type,E1,E2,NameE1,NameE2) :-
			join(C),type(C,Type),
			successor_via_joins(E1,C),event(E1),
			successor_via_joins(E2,C),event(E2),
			E1 @< E2,
			not((
				split(MoreDominatingJoin),
				MoreDominatingJoin\==C,
				successor_via_splits(MoreDominatingJoin,E1),
				successor_via_splits(MoreDominatingJoin,E2),
				every_path_from_a_to_b_must_pass_c(E1,MoreDominatingJoin,C),
				every_path_from_a_to_b_must_pass_c(E2,MoreDominatingJoin,C)
				)),
			elementname(E1,NameE1),
			elementname(E2,NameE2).


connector_and_two_events(C,Type,E1,E2,NameE1,NameE2,SplitOrJoin) :-
			(join_and_two_events(C,Type,E1,E2,NameE1,NameE2),SplitOrJoin=join);
			(split_and_two_events(C,Type,E1,E2,NameE1,NameE2),SplitOrJoin=split).

% zum Testen: canonical(['Der Kasper ist da!',true],[NF,IsSame]).
% Beziehung für "a>=b trifft auf b>=a"

% A ist die Negation von B
% Fall 1: "Un-" etc. im Text

negation(A,B) :- canonical([A,true],[ANF,AIsSame]),
		canonical([B,true],[BNF,BIsSame]),
		(
		% Fall 1: "Un-" etc. im Text
		(negator(Neg),string_to_atom(NegString,Neg),string_to_atom(EmptyString,''),
		 AIsSame==BIsSame,
		 replace_substring(ANF,NegString,EmptyString,BNF)
		)
		;
		% Fall 2: Normalform-Strings sind gleich, aber Negation durch IsSame ausgedrückt
		(
		ANF==BNF,AIsSame\==BIsSame
		)
		;
		% Fall3: a>b ist das Gegenteil von b>=a
		(
		((Comperator1='nf_gt',Comperator2='nf_lt');
		 (Comperator1='nf_lt',Comperator2='nf_gt')),
		sub_string(ANF,ABefore,5,_,Comperator1),
		sub_string(BNF,BBefore,5,_,Comperator2),
		sub_string(ANF,0,ABefore,_,LeftA),
		plus(ABefore,5,StartRightA),
		sub_string(ANF,StartRightA,_,0,RightA),
		sub_string(BNF,0,BBefore,_,LeftB),
		plus(BBefore,5,StartRightB),
		sub_string(BNF,StartRightB,_,0,RightB),
		RightA == LeftB,LeftA == RightB,AIsSame\==BIsSame
		)
		).

equivalent(A,B) :- canonical([A,true],[ANF,AIsSame]),
		canonical([B,true],[BNF,BIsSame]),
		% Fall 1: "Un-" etc. im Text
		((negator(Neg),string_to_atom(NegString,Neg),string_to_atom(EmptyString,''),
		 AIsSame\==BIsSame,
		 (replace_substring(ANF,NegString,EmptyString,BNF)
		 ;
		  replace_substring(BNF,NegString,EmptyString,ANF)
		 )
		)
		;
		% Fall 2: Normalform-Strings sind gleich, keine Negation
		(
		ANF==BNF,AIsSame==BIsSame
		)
		;
		% Fall3: a>b ist das selbe wie b<a
		(
		((Comperator1='nf_gt',Comperator2='nf_lt');(Comperator1='nf_lt',Comperator2='nf_gt')),
		sub_string(ANF,ABefore,5,_,Comperator1),
		sub_string(BNF,BBefore,5,_,Comperator2),
		sub_string(ANF,0,ABefore,_,LeftA),
		plus(ABefore,5,StartRightA),
		sub_string(ANF,StartRightA,_,0,RightA),
		sub_string(BNF,0,BBefore,_,LeftB),
		plus(BBefore,5,StartRightB),
		sub_string(BNF,StartRightB,_,0,RightB),
		RightA == LeftB,LeftA == RightB
		)
		).
% A ist immer "nf_gleichbleibend", B ist immer nf_anstieg, C ist immer nf_abnahme
trinity(A,B,C) :- (canonical([A,true],[ANF,true]),
		canonical([B,true],[BNF,true]),
		canonical([C,true],[CNF,true]),
		sub_string(ANF,_,_,_,'nf_gleichbleibend')),
		replace_substring(ANF,'nf_gleichbleibend','nf_anstieg',BNF),
		replace_substring(ANF,'nf_gleichbleibend','nf_abnahme',CNF).

% A ist immer "="
trinity(A,B,C) :- 
		(canonical([A,true],[ANF,true]),
		canonical([B,true],[BNF,true]),
		canonical([C,true],[CNF,true]),
		%print(ANF),nl,
		%print(BNF),nl,
		%print(CNF),nl,
		sub_string(ANF,ABefore,5,_,'nf_eq')),
		% Fall 1: a=b / a<b / a>b
		((replace_substring(ANF,'nf_eq','nf_lt',BNF),
		 replace_substring(ANF,'nf_eq','nf_gt',CNF)
		 )
		;
		% Fall 2: a=b / a>b / a<b
		 (replace_substring(ANF,'nf_eq','nf_gt',BNF),
		 replace_substring(ANF,'nf_eq','nf_lt',CNF)
		 )
		; % Fall 3: a=b / a<b / b<a bzw. a=b / a>b / b>a
		((Comperator='nf_lt';Comperator='nf_gt'),
		 replace_substring(ANF,'nf_eq',Comperator,BNF),
		 sub_string(CNF,CBefore,5,_,Comperator),
		 sub_string(ANF,0,ABefore,_,LeftA),
		 plus(ABefore,5,StartRightA),
		 sub_string(ANF,StartRightA,_,0,RightA),
		 sub_string(CNF,0,CBefore,_,LeftC),
		 plus(CBefore,5,StartRightC),
		 sub_string(CNF,StartRightC,_,0,RightC),
		 RightA == LeftC,LeftA == RightC
		)
		; % Fall 4: b=a / b<a / a<b bzw. b=a / b>a / a>b
		((Comperator='nf_lt';Comperator='nf_gt'),
		 replace_substring(ANF,'nf_eq',Comperator,CNF),
		 sub_string(BNF,BBefore,5,_,Comperator),
		 sub_string(ANF,0,ABefore,_,LeftA),
		 plus(ABefore,5,StartRightA),
		 sub_string(ANF,StartRightA,_,0,RightA),
		 sub_string(BNF,0,BBefore,_,LeftB),
		 plus(BBefore,5,StartRightB),
		 sub_string(BNF,StartRightB,_,0,RightB),
		 RightA == LeftB,LeftA == RightB
		)
		).

% A ist immer "nf_gleichbleibend", B ist immer nf_anstieg, C ist immer nf_abnahme
trinity_minus_equality(A,B) :- 
		(canonical([A,true],[ANF,true]),
		canonical([B,true],[BNF,true]),
		sub_string(ANF,_,_,_,'nf_anstieg')),
		replace_substring(ANF,'nf_anstieg','nf_abnahme',BNF).

% 
trinity_minus_equality(A,B) :- canonical([A,true],[ANF,true]),
		canonical([B,true],[BNF,true]),
		(replace_substring(ANF,'nf_lt','nf_gt',BNF);
		replace_substring(ANF,'nf_gt','nf_lt',BNF);
		(
		 (Comperator='nf_lt';Comperator='nf_gt'),
		 sub_string(ANF,ABefore,5,_,Comperator),
		 sub_string(ANF,0,ABefore,_,LeftA),
		 plus(ABefore,5,StartRightA),
		 sub_string(ANF,StartRightA,_,0,RightA),
		 sub_string(BNF,0,BBefore,_,LeftB),
		 plus(BBefore,5,StartRightB),
		 sub_string(BNF,StartRightB,_,0,RightB),
		 RightA == LeftB,LeftA == RightB
		)
		).

trivialereignis(A) :- canonical([A,true],[ANF,true]),string_to_atom(ANF,ANFAtom),trivial(ANFAtom).
trivial(' nf_beendet ').
trivial(' nf_ok ').
trivial(' ende ').


%===============================================================================================
% Negatoren, Synonyme und Antonyme
%===============================================================================================

negator('un').
negator('nicht').
negator('nicht-').

% Die Reihenfolge ist wichtig, da sonst von einem "<=" zunächst das "<" bearbeitet würde
% ebenso für kein / ein
replacement(Replace,By,IsSame) :- antonym(Replace,By),IsSame=fail.
replacement(Replace,By,IsSame) :- synonym(Replace,By),IsSame=true.


synonym('  ',' ').
synonym('A','a').
synonym('B','b').
synonym('C','c').
synonym('D','d').
synonym('E','e').
synonym('F','f').
synonym('G','g').
synonym('H','h').
synonym('I','i').
synonym('J','j').
synonym('K','k').
synonym('L','l').
synonym('M','m').
synonym('N','n').
synonym('O','o').
synonym('P','p').
synonym('Q','q').
synonym('R','r').
synonym('S','s').
synonym('T','t').
synonym('U','u').
synonym('V','v').
synonym('W','w').
synonym('X','x').
synonym('Y','y').
synonym('Z','z').
synonym('Ä','ä').
synonym('Ö','ö').
synonym('Ü','ü').
synonym('\n',' ').

% zum Verhindern, dass die folgenden Phrasen ersetzt werden, muss daher vor den anderen stehen:
% TODO: Kontrolle analog
synonym(' der pruefung ',' nf_pruefungsobjekt ').
synonym(' einer pruefung ',' nf_pruefungsobjekt ').
synonym(' zur pruefung ',' nf_pruefungsobjekt ').
synonym(' auf die pruefung ',' nf_pruefungsobjekt ').
synonym(' des reviews ',' nf_pruefungsobjekt ').
synonym(' eines reviews ',' nf_pruefungsobjekt ').
synonym(' zum review ',' nf_pruefungsobjekt ').
synonym(' der validierung ',' nf_pruefungsobjekt ').
synonym(' einer validierung ',' nf_pruefungsobjekt ').
synonym(' zur validierung ',' nf_pruefungsobjekt ').
synonym(' auf die validierung ',' nf_pruefungsobjekt ').

synonym(' anfragen ',' nf_anfrage ').
synonym(' abfragen ',' nf_anfrage ').
synonym(' erfragen ',' nf_anfrage ').
synonym(' anfrage ',' nf_anfrage ').
synonym(' abfrage ',' nf_anfrage ').

synonym(' sowie ',' und ').
synonym(' nf_erlaubnis nf_erlaubnis ',' nf_erlaubnis '). % fuer Faelle wie "Zusage erteilt"
synonym(' erlaubt ',' nf_erlaubnis ').
synonym(' zu erlauben ',' nf_zu_erlauben ').
synonym(' zu erlauben ',' nf_zu_erlauben ').
synonym(' erlauben ',' nf_erlaubnis '). % Beachte immder die Reihenfolge: laengeres vor kuerzerem Teilwort
synonym(' erlaube ',' nf_erlaubnis ').
synonym(' erlaubnis ',' nf_erlaubnis ').
synonym(' akzeptiert ',' nf_erlaubnis ').
synonym(' zu anzunehmen ',' nf_zu_erlauben ').
synonym(' akzeptieren ',' nf_erlaubnis ').
synonym(' akzeptiere ',' nf_erlaubnis ').
synonym(' zu genehmigen ',' nf_zu_erlauben ').
synonym(' genehmigt ',' nf_erlaubnis ').
synonym(' genehmige ',' nf_erlaubnis ').
synonym(' genehmigen ',' nf_erlaubnis ').
synonym(' genehmigung ',' nf_erlaubnis ').
synonym(' zu bewilligen ',' nf_zu_erlauben ').
synonym(' bewilligt ',' nf_erlaubnis ').
synonym(' bewilligen ',' nf_erlaubnis ').
synonym(' bewillige ',' nf_erlaubnis ').
synonym(' bewilligung ',' nf_erlaubnis ').
synonym(' zu erteilen ',' nf_zu_erlauben ').
synonym(' erteilt ',' nf_erlaubnis ').
synonym(' erteilen ',' nf_erlaubnis ').
synonym(' erteile ',' nf_erlaubnis ').
synonym(' zu gewaehren ',' nf_zu_erlauben ').
synonym(' gewaehrt ',' nf_erlaubnis ').
synonym(' gewaehren ',' nf_erlaubnis ').
synonym(' gewaehre ',' nf_erlaubnis ').
synonym(' zu befuerworten ',' nf_zu_erlauben ').
synonym(' befuerwortet ',' nf_erlaubnis ').
synonym(' befuerworten ',' nf_erlaubnis ').
synonym(' befuerworte ',' nf_erlaubnis ').
synonym(' zuzusagen ',' nf_zu_erlauben ').
synonym(' zugesagt ',' nf_erlaubnis ').
synonym(' zusagen ',' nf_erlaubnis ').
synonym(' zusage ',' nf_erlaubnis ').
synonym(' sagt zu ',' nf_erlaubnis ').
synonym(' sagen zu ',' nf_erlaubnis ').
synonym(' sagte zu ',' nf_erlaubnis ').
synonym(' sagten zu ',' nf_erlaubnis ').
synonym(' zuzustimmen ',' nf_zu_erlauben ').
synonym(' zugestimmt ',' nf_erlaubnis ').
synonym(' zustimmen ',' nf_erlaubnis ').
synonym(' zustimmung ',' nf_erlaubnis ').
synonym(' zuzustimmen ',' nf_erlaubnis ').
synonym(' stimmt zu ',' nf_erlaubnis ').
synonym(' stimmen zu ',' nf_erlaubnis ').
synonym(' stimmte zu ',' nf_erlaubnis ').
synonym(' stimmten zu ',' nf_erlaubnis ').
synonym(' anzunehmen ',' nf_zu_erlauben ').
synonym(' angenommen ',' nf_erlaubnis ').
synonym(' annahme ',' nf_erlaubnis ').
synonym(' annehmen ',' nf_erlaubnis ').
synonym(' anzunehmen ',' nf_erlaubnis ').
synonym(' stattgeben ',' nf_erlaubnis ').
synonym(' stattgegeben ',' nf_erlaubnis ').
synonym(' stattzugeben ',' nf_zu_erlauben ').
synonym(' erfuellt ',' nf_erfuellt ').
synonym(' gegeben ',' nf_erfuellt ').
synonym(' gewaehrleistet ',' nf_erfuellt ').
synonym(' mit erfolg ',' erfolgreich ').
synonym(' manuell',' nf_manuell'). % kein Leerzeichen am Ende
synonym(' von hand ',' nf_manuell ').
synonym(' maschinell',' nf_maschinell').  % kein Leerzeichen am Ende
synonym(' automatisch',' nf_maschinell'). % kein Leerzeichen am Ende
synonym(' automatisiert',' nf_maschinell'). % kein Leerzeichen am Ende
synonym(' notwendig ',' nf_notwendig ').
synonym(' erforderlich ',' nf_notwendig ').
synonym(' noetig ',' nf_notwendig ').
synonym(' erfolgreich beendet ',' nf_beendet ').
synonym(' beendet ',' nf_beendet ').
synonym(' erfolgreich erledigt ',' nf_beendet ').
synonym(' erledigt ',' nf_beendet ').
synonym(' erfolgreich durchgefuehrt ',' nf_beendet ').
synonym(' durchgefuehrt ',' nf_beendet ').
synonym(' erfolgreich ausgefuehrt ',' nf_beendet ').
synonym(' ausgefuehrt ',' nf_beendet ').
synonym(' erfolgreich abgeschlossen ',' nf_beendet ').
synonym(' abgeschlossen ',' nf_beendet ').
synonym(' fertiggestellt  ',' nf_beendet ').
synonym(' fertig gestellt  ',' nf_beendet ').
synonym(' fertig ',' nf_beendet ').
synonym(' info ', 'information ').
synonym(' infos ', 'informationen ').
synonym(' widerspruch ',' nf_widerspruch ').
synonym(' einspruch ',' nf_widerspruch ').
synonym(' rekurs ',' nf_widerspruch ').
synonym(' weiterfuehren ',' fortsetzen ').
synonym(' einfuegen ',' hinzufuegen ').
synonym(' einzufuegen ',' hinzuzufuegen ').
synonym(' eingefuegt ',' hinzugefuegt ').
synonym(' bereits vorhanden ',' nf_vorhanden ').
synonym(' vorhanden ',' nf_vorhanden ').
synonym(' liegen vor ',' nf_vorhanden ').
synonym(' liegt vor ',' nf_vorhanden ').
synonym(' existiert ',' nf_vorhanden ').
synonym(' existieren ',' nf_vorhanden ').
synonym(' eingetroffen ', ' nf_eingetroffen ').
synonym(' eingegangen ', ' nf_eingetroffen ').
synonym(' eingelangt ', ' nf_eingetroffen ').
synonym(' traf ein ', ' nf_eingetroffen ').
synonym(' trafen ein ', ' nf_eingetroffen ').
synonym(' ging ein ', ' nf_eingetroffen ').
synonym(' gingen ein ', ' nf_eingetroffen ').
synonym(' datenumschluesselung ',' nf_konvertierung ').
synonym(' datenkonvertierung ',' nf_konvertierung ').
synonym(' konvertierung ',' nf_konvertierung ').
synonym(' beginnen ',' nf_beginnen ').
synonym(' beginnt ',' nf_beginnen ').
synonym(' starten ',' nf_beginnen ').
synonym(' startet ',' nf_beginnen ').
synonym(' beenden ',' nf_beenden ').
synonym(' beendet ',' nf_beenden ').
synonym(' durchfuehren ',' nf_durchfuehren ').
synonym(' ausfuehren ',' nf_durchfuehren ').
synonym(' schon ',' bereits ').
synonym(' gleich ',' nf_eq ').
synonym('=',' nf_eq ').
synonym(' identisch ',' nf_eq ').
synonym(' stimmen ueberein ',' nf_eq ').
synonym(' stimmen miteinander ueberein ',' nf_eq ').
synonym(' identisch',' gleich'). % kein Leerzeichen am Ende, muss nach den obigen stehen ---> gleicher etc.
synonym(' uebereinstimmend',' gleich'). % kein Leerzeichen am Ende, muss nach den obigen stehen
synonym(' pruefung ',' nf_pruefung ').
synonym(' review ',' nf_pruefung ').
synonym(' ueberpruefung ',' nf_pruefung ').
synonym(' validierung ',' nf_pruefung ').
synonym(' verifizierung ',' nf_pruefung ').
synonym(' verifikation ',' nf_pruefung ').
synonym(' pruefen ',' nf_pruefung ').
synonym(' ueberpruefen ',' nf_pruefung ').
synonym(' nachpruefen ',' nf_pruefung ').
synonym(' validieren ',' nf_pruefung ').
synonym(' verifizieren ',' nf_pruefung ').
synonym(' fristgemaess ',' nf_fristgemaess ').
synonym(' rechtzeitig ',' nf_fristgemaess ').
synonym(' fristgerecht ',' nf_fristgemaess ').
synonym(' puenktlich',' nf_fristgemaess ').
synonym(' termingemaess',' nf_fristgemaess ').
synonym(' termingerecht ',' nf_fristgemaess ').
synonym(' ok ',' nf_ok ').
synonym(' o k ',' nf_ok ').
synonym(' in ordnung ',' nf_ok ').
synonym(' io ',' nf_ok ').
synonym(' i.o. ',' nf_ok ').
synonym(' i o ',' nf_ok ').
synonym(' wahr',' nf_ok'). % kein Leerzeichen am Ende --> wahres etc.
synonym(' richtig',' nf_ok').  % kein Leerzeichen am Ende
synonym(' korrekt',' nf_ok').  % kein Leerzeichen am Ende
synonym(' fehlerfrei',' nf_ok').  % kein Leerzeichen am Ende
synonym(' ohne fehler ',' nf_ok ').
synonym('>',' nf_gt ').
synonym('&gt;',' nf_gt ').
synonym('groesser als ',' nf_gt ').
synonym(' groesser als ',' nf_gt ').
synonym(' uebersteigt ',' nf_gt ').
synonym(' uebersteigen ',' nf_gt ').
synonym(' ueberstieg ',' nf_gt ').
synonym(' ueberstiegen ',' nf_gt ').
synonym(' ueberschreitet ',' nf_gt ').
synonym(' ueberschreiten ',' nf_gt ').
synonym(' ueberschritt ',' nf_gt ').
synonym(' ueberschritten ',' nf_gt ').
synonym(' mehr als ',' nf_gt ').
synonym('nf_gt 0 ',' positiv ').
synonym('nf_gt null ',' positiv ').
synonym('<',' nf_lt ').
synonym('&lt;',' nf_lt ').
synonym(' kleiner als ',' nf_lt ').
synonym(' unterschreitet ',' nf_lt ').
synonym(' unterschreiten ',' nf_lt ').
synonym(' unterschritt ',' nf_lt ').
synonym(' unterschritten ',' nf_lt ').
synonym(' weniger als ',' nf_lt ').
synonym(' geringer als ',' nf_lt ').
synonym(' hat sich nf_anstieg ',' nf_anstieg ').
synonym(' haben sich nf_anstieg ',' nf_anstieg ').
synonym(' vergroesserte sich ',' nf_anstieg ').
synonym(' vergroessert',' nf_anstieg'). % ohne Leerzeichen am Ende
synonym(' erhoehte sich ',' nf_anstieg ').
synonym(' erhoeht',' nf_anstieg'). % ohne Leerzeichen am Ende
synonym(' steigerte sich ',' nf_anstieg ').
synonym(' gesteigert',' nf_anstieg'). % ohne Leerzeichen am Ende
synonym(' angestiegen',' nf_anstieg'). % ohne Leerzeichen am Ende
synonym(' gestiegen',' nf_anstieg'). % ohne Leerzeichen am Ende
synonym(' zugenommen',' nf_anstieg'). % ohne Leerzeichen am Ende
synonym(' steigend',' nf_anstieg'). % ohne Leerzeichen am Ende
synonym(' stiegen ',' nf_anstieg ').
synonym(' stieg ',' nf_anstieg ').
synonym(' nahmen zu ',' nf_anstieg ').
synonym(' nahm zu ',' nf_anstieg ').
synonym(' zunehmend',' nf_anstieg'). % ohne Leerzeichen am Ende
synonym(' hat sich nf_abnahme ',' nf_abnahme ').
synonym(' haben sich nf_abnahme ',' nf_abnahme ').
synonym(' verringerten sich ',' nf_abnahme ').
synonym(' verringerte sich ',' nf_abnahme ').
synonym(' verringert',' nf_abnahme').  % ohne Leerzeichen am Ende
synonym(' verkleinerten sich ',' nf_abnahme ').
synonym(' verkleinerte sich ',' nf_abnahme ').
synonym(' verkleinert',' nf_abnahme'). % ohne Leerzeichen am Ende
synonym(' gefallen',' nf_abnahme'). % ohne Leerzeichen am Ende
synonym(' gekuerzt',' nf_abnahme'). % ohne Leerzeichen am Ende
synonym(' verkuerzt',' nf_abnahme'). % ohne Leerzeichen am Ende
synonym(' abgenommen',' nf_abnahme'). % ohne Leerzeichen am Ende
synonym(' abnehmend',' nf_abnahme'). % ohne Leerzeichen am Ende
synonym(' nahm ab ',' nf_abnahme ').
synonym(' nahmen ab ',' nf_abnahme ').
synonym(' fielen ',' nf_abnahme ').
synonym(' fiel ',' nf_abnahme ').
synonym(' gefallen ',' nf_abnahme ').
synonym(' fallend',' nf_abnahme'). % ohne Leerzeichen am Ende
synonym(' sank ',' nf_abnahme ').
synonym(' sanken ',' nf_abnahme ').
synonym(' gesunken',' nf_abnahme'). % ohne Leerzeichen am Ende
synonym(' sinkend',' nf_abnahme'). % ohne Leerzeichen am Ende
synonym(' bleibt unveraendert ',' nf_gleichbleibend ').
synonym(' blieben unveraendert ',' nf_gleichbleibend ').
synonym(' blieben unveraendert ',' nf_gleichbleibend ').
synonym(' blieb unveraendert ',' nf_gleichbleibend').
synonym(' unveraendert geblieben ',' nf_gleichbleibend').
synonym(' unveraendert',' nf_gleichbleibend').
synonym(' bleibt nf_eq ',' nf_gleichbleibend ').
synonym(' bleiben nf_eq ',' nf_gleichbleibend ').
synonym(' blieben nf_eq ',' nf_gleichbleibend ').
synonym(' blieb nf_eq ',' nf_gleichbleibend ').
synonym(' nf_eq geblieben ',' nf_gleichbleibend ').
synonym(' bleibt konstant ',' nf_gleichbleibend ').
synonym(' bleiben konstant ',' nf_gleichbleibend ').
synonym(' blieben konstant ',' nf_gleichbleibend ').
synonym(' blieb konstant ',' nf_gleichbleibend ').
synonym(' konstant geblieben ',' nf_gleichbleibend ').
synonym(' mindestens ein',' ein'). % ohne Leerzeichen
synonym(' moeglicherweise ',' nf_eventuell '). % wird auch benötigt für restriction/1
synonym(' eventuell ',' nf_eventuell ').
synonym(' vielleicht ',' nf_eventuell ').
synonym(' moeglichenfalls ',' nf_eventuell ').
synonym(' unter umstaenden ',' nf_eventuell ').
synonym(' unter vorbehalten ',' nf_eventuell '). % Reihenfolge beachten!
synonym(' ermessen ',' nf_ermessensspielraum '). % wird auch benötigt für restriction/1
synonym(' ermessenssache ',' nf_ermessensspielraum ').
synonym(' ermessensspielraum ',' nf_ermessensspielraum ').
synonym(' entscheidungsspielraum ',' nf_ermessensspielraum ').
synonym(' unter dem vorbehalt ',' nf_eventuell ').
synonym(' unter vorbehalt ',' nf_eventuell ').
synonym(' nur nf_teilweise ',' nf_teilweise '). % wird auch benötigt für restriction/1
synonym(' eingeschraenkt',' nf_teilweise ').
synonym(' mit auflagen ',' nf_teilweise ').
synonym(' unter auflagen ',' nf_teilweise ').
synonym(' mit einschraenkungen ',' nf_teilweise ').
synonym(' unter einschraenkungen ',' nf_teilweise ').
synonym(' zum teil ',' nf_teilweise ').
synonym(' teilweises ',' nf_teilweise ').  % Verzicht auf Leerzeichen würde hier zu Endlosschleife führen, Reihenfolge beachten
synonym(' teilweisen ',' nf_teilweise ').
synonym(' teilweisem ',' nf_teilweise ').
synonym(' teilweise ',' nf_teilweise ').
synonym(' partiell',' nf_teilweise').  % kein Leerzeichen am Ende
synonym(' lueckenhaft',' nf_teilweise'). % kein Leerzeichen am Ende
synonym(' unvollstaendig',' nf_teilweise'). % kein Leerzeichen am Ende
synonym(' z t  ',' nf_teilweise ').


synonym(Stopword,' ') :- stopword(Stopword).
% Stop Words:
stopword(' der ').
stopword(' die ').
stopword(' das ').
stopword(' des ').
stopword(' dem ').
stopword(' den ').
stopword(' einen ').
stopword(' einem ').
stopword(' einer ').
stopword(' eines ').
stopword(' eine ').
stopword(' ein ').
stopword(' wurden ').
stopword(' wurde ').
stopword(' ist ').
stopword(' sind ').
stopword(' es '). % Zum Satzanfang: z.B. Es wurde ein Antrag gestellt
stopword(' noch ').% noch zu erledigen / noch nicht
stopword(' schon '). % im Gegensatz zu noch nicht
stopword(' bereits ').
stopword('.').
stopword(',').
stopword(';').
stopword('!').
stopword('hat sich'). % wichtig fuer verringert, erhoeht

antonym(' zu verbieten ',' nf_zu_erlauben ').
antonym(' verboten ',' nf_erlaubnis ').
antonym(' verbieten ',' nf_erlaubnis ').
antonym(' verbiete ',' nf_erlaubnis ').
antonym(' ablehnen ',' nf_erlaubnis ').
antonym(' abzulehnen ',' nf_zu_erlauben ').
antonym(' abgelehnt ',' nf_erlaubnis ').
antonym(' abgelehnt ',' nf_erlaubnis ').
antonym(' ablehnung ',' nf_erlaubnis ').
antonym(' zu verwehren ',' nf_zu_erlauben ').
antonym(' verwehrt ',' nf_erlaubnis ').
antonym(' verwehren ',' nf_erlaubnis ').
antonym(' verwehre ',' nf_erlaubnis ').
antonym(' zu versagen ',' nf_zu_erlauben ').
antonym(' versagt ',' nf_erlaubnis ').
antonym(' versagen ',' nf_erlaubnis ').
antonym(' versage ',' nf_erlaubnis ').
antonym(' untersagt ',' nf_erlaubnis ').
antonym(' zu untersagen ',' nf_zu_erlauben ').
antonym(' untersagen ',' nf_erlaubnis ').
antonym(' untersage ',' nf_erlaubnis ').
antonym(' zu verweigern ',' nf_zu_erlauben ').
antonym(' verweigert ',' nf_erlaubnis ').
antonym(' verweigern ',' nf_erlaubnis ').
antonym(' verweigere ',' nf_erlaubnis ').
antonym(' abzusagen ',' nf_zu_erlauben ').
antonym(' absagen ',' nf_erlaubnis ').
antonym(' absage ',' nf_erlaubnis ').
antonym(' sagen ab ',' nf_erlaubnis ').
antonym(' sagte ab ',' nf_erlaubnis ').
antonym(' sagten ab ',' nf_erlaubnis ').
antonym(' lehnt ab ',' nf_erlaubnis ').
antonym(' lehnen ab ',' nf_erlaubnis ').
antonym(' lehnte ab ',' nf_erlaubnis ').
antonym(' lehnten ab ',' nf_erlaubnis ').
antonym(' zurueckweisen ',' nf_erlaubnis ').
antonym(' zurueckgewiesen',' nf_erlaubnis ').
antonym(' zurueckzuweisen',' nf_zu_erlauben ').
% Beachte die Reihenfolge  der 3 folgenden Zeilen: noch nicht / liegt noch vor
antonym(' noch nicht ',' bereits '). 
antonym(' noch ',' nicht mehr ').
antonym(' nicht ',' '). % siehe auch oben unter negator()
antonym(' kein ',' ein ').
antonym(' keine ',' eine ').
antonym(' keinen ',' einen ').
antonym(' nein ',' ja ').
antonym(' ohne ',' mit ').
antonym(' bejaht ',' verneint ').
antonym(' erfolglos',' erfolgreich').  % kein Leerzeichen am Ende
antonym(' gescheitert',' erfolgreich').  % kein Leerzeichen am Ende
antonym(' abbrechen',' fortsetzen').
antonym(' abgebrochen',' fortgesetzt').
antonym(' gesperrt ',' freigegeben').
antonym(' konsistent',' inkonsistent'). % kein Leerzeichen am Ende
antonym(' direkt',' indirekt'). % kein Leerzeichen am Ende
antonym(' verspaetet',' nf_fristgemaess'). % kein Leerzeichen am Ende
antonym(' nachtraeglich',' nf_fristgemaess'). % kein Leerzeichen am Ende
antonym(' verzoegert',' nf_fristgemaess'). % kein Leerzeichen am Ende
antonym(' unpuenktlich',' nf_fristgemaess'). % kein Leerzeichen am Ende
antonym(' defekt',' nf_ok'). % kein Leerzeichen am Ende
antonym(' fehlerhaft',' nf_ok'). % kein Leerzeichen am Ende
antonym(' mit fehlern ',' nf_ok ').
antonym(' mit fehler ',' nf_ok ').
antonym(' enthaelt fehler ',' nf_ok ').
antonym(' enthalten fehler ',' nf_ok ').
antonym(' falsch',' nf_ok'). % kein Leerzeichen am Ende --> falsche, falsches etc erfasst
antonym(' ungenau',' nf_ok').  % kein Leerzeichen am Ende
antonym(' inkorrekt',' nf_ok'). % kein Leerzeichen am Ende
antonym(' komplett ',' nf_teilweise ').
antonym(' vollstaendig ',' nf_teilweise ').
antonym(' in vollem umfang ',' nf_teilweise ').
antonym(' in vollem umfange ',' nf_teilweise ').
antonym(' zu 100 Prozent ',' nf_teilweise ').
antonym(' zu 100% ',' nf_teilweise ').
antonym(' zu 100 % ',' nf_teilweise ').
antonym(' gaenzlich ',' nf_teilweise ').
antonym(' verschieden',' gleich'). % kein Leerzeichen am Ende und nicht sofort zu nf_eq (gleicher...)
antonym(' voneinander abweichend',' gleich'). % kein Leerzeichen am Ende und nicht sofort zu nf_eq (gleicher...)
antonym(' unterschiedlich',' gleich'). % kein Leerzeichen am Ende und nicht sofort zu nf_eq (gleicher...)
antonym(' negativ',' positiv'). % kein Leerzeichen am Ende --> negatives etc. erfasst
antonym('nf_lt 0 ',' positiv ').
antonym('nf_lt null ',' positiv ').
antonym('<=',' nf_gt ').
antonym(' kleiner oder gleich ',' nf_gt ').
antonym(' kleiner / gleich ',' nf_gt ').
antonym(' kleiner/gleich ',' nf_gt ').
antonym('>=',' nf_lt ').
antonym(' groesser oder gleich ',' nf_lt ').
antonym(' groesser / gleich ',' nf_lt ').
antonym(' groesser/gleich ',' nf_lt ').
antonym(' keine ',' eine ').
antonym(' kein ',' ein ').
antonym(' innerhalb ',' ausserhalb ').

restriction('nf_teilweise').
restriction('nf_eventuell').
restriction('nf_ermessensspielraum').
restriction('teilzusage').


canonical([N,S],[NN,SS]) :- 	string_to_atom(Space,' '),
				string_concat(Space,N,SpaceN),
				string_concat(SpaceN,Space,SpaceNSpace),  % Nun ist am Anfang und Ende des Strings ein Leerzeichen
				canon([SpaceNSpace,S],[NN,SS]).

canon([N,S],[NN,SS]) :- (   
				replacement(X,Y,IsSame),
				string_to_atom(Replace,X),string_to_atom(By,Y),
				replace_substring(N,Replace,By,N2),
				((IsSame,S = S2);(not(S), S2 = true);(S, S2 = fail)),
				canon([N2,S2],[NN,SS]),!
			     );
			      N=NN,S=SS.

% Ersetzen eines Teilstrings durch einen anderen

replace_substring(String,Substring,Replacement,Result) :- sub_string(String,Start,Len,_,Substring),
	plus(Start,Len,Offset),
	sub_string(String,Offset,_,0,Part2),
	sub_string(String,0,Start,_,Part1),
	string_concat(Part1,Replacement,Part1b),
	string_concat(Part1b,Part2,Result).


replace_all_substrings(String,From,To,Result) :- string_to_list(String,StringList),
				string_to_list(From,FromList),
				string_to_list(To,ToList),
				replace_all(StringList,FromList,ToList,ResultList),
				string_to_list(Result,ResultList).

replace(InputList, From, To, ResultList) :- append(From, After, FromAndAfter), % make something like a difference list
					append(Before, FromAndAfter, InputList), % search for a match
					append(Before, To, BeforeAndTo), % and finally, compose the output string
					append(BeforeAndTo, After, ResultList).

replace([], _, _, []).

replace_all(InputList, From, To, ResultList) :- append(From, After, FromAndAfter),
					append(Before, FromAndAfter, InputList),
					append(Before, To, BeforeAndTo),
					append(BeforeAndTo, FinalAfter, ResultList),
					!,
					replace_all(After, From, To, FinalAfter).

replace_all(InputList, _, _, InputList).

% Wenn String nicht gebunden ist, wird es zum Default-String 'nf_undef').
% Somit kann trinity(Ax,Bx,Cx) auch sinnvoll verwendet werden, wenn nur zwei der Argumente
% tatsächlich gebunden sind.
initialize(String,String2) :- (atom(String),String2 = String); String2='nf_undef'.



%===========================================================================================================
% Metriken
%===========================================================================================================
metric :- metric_number_of_nodes.
metric :- metric_number_of_functions.
metric :- metric_number_of_events.
metric :- metric_number_of_startevents.

metric_number_of_nodes			:- tocheck(metric_number_of_nodes),
				findall(N,(function(N);event(N);connector(N)),L),length(L,Len),
				compose_message(de,Message,
				[
				'Anzahl der Modellelemente: ',
				Len
				]),
				compose_message(en,Message,
				[
				'Number of nodes: ',
				Len
				]),
				message('INFO','',Message,'metric_number_of_nodes'),!.

metric_number_of_functions	:- tocheck(metric_number_of_functions),
				findall(N,(function(N)),L),length(L,Len),
				compose_message(de,Message,
				[
				'Anzahl der Funktionen: ',
				Len
				]),
				compose_message(en,Message,
				[
				'Number of functions: ',
				Len
				]),
				message('INFO','',Message,'metric_number_of_functions'),!.

metric_number_of_events		:- tocheck(metric_number_of_events),
				findall(N,(event(N)),L),length(L,Len),
				compose_message(de,Message,
				[
				'Anzahl der Ereignisse: ',
				Len
				]),
				compose_message(en,Message,
				[
				'Number of events: ',
				Len
				]),
				message('INFO','',Message,'metric_number_of_events'),!.

metric_number_of_startevents	:- tocheck(metric_number_of_startevents),
				findall(N,(startevent(N)),L),length(L,Len),
				compose_message(de,Message,
				[
				'Anzahl der Startereignisse: ',
				Len
				]),
				compose_message(en,Message,
				[
				'Number of start events: ',
				Len
				]),
				message('INFO','',Message,'metric_number_of_startevents'),!.

metric_well_structured		:- tocheck(metric_well_structured),
				(not(reduced);clause(unstructured,true)),
				compose_message(de,Message,
				[
				'EPK ist wohlstrukturiert: NEIN'
				]),
				compose_message(en,Message,
				[
				'EPC is well-structured: NO'
				]),
				message('INFO','',Message,'metric_well_structured'),!.

metric_well_structured		:- tocheck(metric_well_structured),
				(reduced,not(clause(unstructured,true))),
				compose_message(de,Message,
				[
				'EPK ist wohlstrukturiert: JA'
				]),
				compose_message(en,Message,
				[
				'EPC is well-structured: YES'
				]),
				message('INFO','',Message,'metric_well_structured'),!.

