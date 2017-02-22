:- use_module(library(clpfd)).


% For testing purposes only:
% language(en).

%===========================================================================================================
% The program is called by addon_query(Language,List_of_Tests).
%===========================================================================================================
addon_query(Language,List_of_Tests) :-  (retractall(language(_)),assertz(language(Language)),assertz(todo(List_of_Tests)),findall(_,analyze,_),fail);
                                print('#query_end#').

% a/1 is for testing purposes; all Rules will be applied:
a(Language) :-  retractall(language(_)),assertz(language(Language)),findall(_,analyze,_).


analyze :- not_connected.
analyze :- missing_dependum.
analyze :- wrong_arc_to_goal.
analyze :- task_decomposition_direction.
analyze :- decomposition_link_across_actor_boundaries.
analyze :- dependency_link_inside_actor.
analyze :- depencency_link_at_actor_boundary.
analyze :- means_ends_between_goals.
analyze :- contribution_link_between_actors.
analyze :- contribution_link_between_tasks.
analyze :- wrong_softgoal_refinement.
analyze :- dependency_link_direction.
analyze :- dependum_used_in_more_than_one_dependency.
analyze :- means_ends_link_used_wrongly.
analyze :- contribution_link_used_wrongly.
analyze :- softgoal_depends_on_goal.
analyze :- goal_decomposition.
analyze :- goal_or_softgoal_as_leaf.
analyze :- association_link_between_different_types.
analyze :- specific_actor_links.
analyze :- link_direction_does_not_reflect_hierarchy.
analyze :- overlapping_elements.
% analyze :- element_outside_actor_boundaries.
% analyze :-dependum_inside_actor.
analyze :- name_missing.
analyze :- looks_like_goal.
analyze :- looks_like_softgoal.
analyze :- metric_number_of_nodes.


% tocheck tests whether the rule has to be applied.
% This is the case if todo/1 is not defined (in this case, all rules will be applied)
% or if the test name is contained in the call parameter of todo/1.

tocheck(Testname) :-     (delete_all_attributes(Testname)),
                         not(clause(todo(_),true));
                        (clause(todo(_),true),(todo(List),member(Testname,List))).

%===========================================================================================================
% Formatting messages
%===========================================================================================================

% The format for generating text that should be shown in the problems view of openOME is as follows:
% ['PROBLEM'|'WARNING'|'INFO'][ID][Text]
% % [ and ] have been replaced in Text as they have a special meaning 
message(Class,ID,Text,Orig) :-  print('addon:[MESSAGE]'),replace_all_substrings(Text,'\n',' ',TextWithoutNewlines),
                                replace_all_substrings(TextWithoutNewlines,'[','(',Text2),
                                replace_all_substrings(Text2,']',')',Text3),
                                print('['),print(Class),print(']'),
                                print('['),
                                print(ID),
                                print(']'),
                                print('['),print(Text3),print(']'),
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


set_attribute(ID,Rule,Message) :- print('addon:[ATTRIBUTE][SET]['),print(ID),print('][PROBLEM_'),
                                  print(Rule),print(']['),
                                  print(Message),print(']#FS#'),nl.

% delete_all_attributes/1 deletes the user-defined attribute PROBLEM_<Rule> from each element.
% (Is called for each rule from tocheck/1 )
delete_all_attributes(Rule)     :- (task(ID),delete_attributes(ID,Rule),fail);
                                (goal(ID),delete_attributes(ID,Rule),fail);
                                (softgoal(ID),delete_attributes(ID,Rule),fail);
                                (resource(ID),delete_attributes(ID,Rule),fail);
                                (actor(ID),delete_attributes(ID,Rule),fail);
                                (agent(ID),delete_attributes(ID,Rule),fail);
                                (role(ID),delete_attributes(ID,Rule),fail);
                                (position(ID),delete_attributes(ID,Rule));true.
                                
                                  
delete_attributes(ID,Rule) :- print('addon:[ATTRIBUTE][xREMOVE]['),print(ID),print('][PROBLEM_'),
                                  print(Rule),print('][]#FS#'),nl.
%===========================================================================================================
% General Predicates
%===========================================================================================================

makecontribution(X,Y) :- clause(makecontribution(_,X,Y),true).
somepluscontribution(X,Y) :- clause(somepluscontribution(_,X,Y),true).
helpcontribution(X,Y) :- clause(helpcontribution(_,X,Y),true).
hurtcontribution(X,Y) :- clause(hurtcontribution(_,X,Y),true).
someminuscontribution(X,Y) :-  clause(someminuscontribution(_,X,Y),true).
breakcontribution(X,Y) :- clause(breakcontribution(_,X,Y),true).
orcontribution(X,Y) :- clause(orcontribution(_,X,Y),true).
andcontribution(X,Y) :- clause(andcontribution(_,X,Y),true).
unknowncontribution(X,Y) :- clause(unknowncontribution(_,X,Y),true).

positivecontribution(X,Y) :-    makecontribution(X,Y);
                                somepluscontribution(X,Y);
                                helpcontribution(X,Y).


negativecontributioin(X,Y) :-   hurtcontribution(X,Y);
                                someminuscontribution(X,Y);
                                breakcontribution(X,Y).

contribution(X,Y) :-            positivecontribution(X,Y);negativecontributioin(X,Y);
                                orcontribution(X,Y);
                                andcontribution(X,Y);
                                unknowncontribution(X,Y).

isa(X,Y) :- clause(isa(_,X,Y),true).
plays(X,Y) :- clause(plays(_,X,Y),true).
covers(X,Y) :- clause(covers(_,X,Y),true).
ispartof(X,Y) :- clause(ispartof(_,X,Y),true).
instance(X,Y) :- clause(ins(_,X,Y),true).   % note: ins/2 is already used for other purposes by SWI-Prolog
occupies(X,Y) :- clause(occupies(_,X,Y),true).

actorrelation(X,Y) :-           isa(X,Y);
                                plays(X,Y);
                                covers(X,Y);
                                ispartof(X,Y);
                                instance(X,Y);
                                occupies(X,Y).

% (unten,oben) bzw. (Anbieter,Abhaengiger)
dependency(X,Y) :- clause(dependency(_,X,Y),true).
me(X,Y) :- clause(ordecomposition(_,X,Y),true). % means-ends
anddecomposition(X,Y) :- clause(anddecomposition(_,X,Y),true).
contains(X,Y) :- clause(contains(X,Y),true).
arc(X,Y)   :- dependency(X,Y); me(X,Y);anddecomposition(X,Y);contribution(X,Y);actorrelation(X,Y).
uarc(X,Y)  :- arc(X,Y);arc(Y,X).

agent(X) :- clause(agent(X),true).
role(X) :- clause(role(X),true).
position(X) :- clause(position(X),true).
actor(X) :-     agent(X);role(X);position(X). % Note that actor/1 can also be a predicate in the model.

%intentional elements:
goal(X) :- clause(goal(X),true).
softgoal(X) :- clause(softgoal(X),true).
task(X) :- clause(task(X),true).
resource(X) :- clause(resource(X),true).

element(X) :-   goal(X);
                softgoal(X);
                task(X);
                resource(X);
                actor(X).

element_different_from_actor(X) :- goal(X);
                softgoal(X);
                task(X);
                resource(X).
                
% Paths
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


% Neighbourhood contains all elements of List and their neighbours,
% where neighbours are those elements that have either an arc to one of the list member of
% have an "contains" relation to one of the list members.
neighbourhood(List,Neighbourhood) :- findall(N,(member(X,List),(uarc(X,N);contains(X,N);contains(N,X))),Neighbours),
                                          union(List,Neighbours,List_And_Neighbours),
                                          list_to_ord_set(List_And_Neighbours,Neighbourhood).

neighbourhood_within_container(List,Neighbourhood) :- findall(N,(member(X,List),contains(Container,X),uarc(X,N),contains(Container,N)),Neighbours),
                                          union(List,Neighbours,List_And_Neighbours),
                                          list_to_ord_set(List_And_Neighbours,Neighbourhood).

% Find the elements which are weakly connected to at least one element in List

connected_elements(List,X) :- neighbourhood(List,List), X = List.
connected_elements(List,X) :- neighbourhood(List,Neighbourhood), connected_elements(Neighbourhood,X).

%===============================================================================================
% Syntax tests
%===============================================================================================

% Unconnected elements is indicative of an incomplete model
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=270
not_connected :-        tocheck(not_connected),
                        element(E),connected_elements([E],Y),!,element(X),not(member(X,Y)),
                        compose_message(de,Message,
                        [
                        'Das Modell ist nicht zusammenhaengend, was ein Anzeichen fuer ein unvollstaendiges Modell ist. '
                        ]),
                        compose_message(en,Message,
                        [
                        'The model is not a coherent graph! Unconnected elements is indicative of an incomplete model.'
                        ]),
                        message('WARNING','',Message,'not_connected').

% Do not use a Dependency Link between two actors without showing the Dependum
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=241

missing_dependum :-     tocheck(missing_dependum),
                        (actor(E)
                        ;
                        (element_different_from_actor(E),contains(_,E))
                        ),
                        (dependency(E,Partner);dependency(Partner,E)),
                        (
                         actor(Partner)
                         ;
                         (element_different_from_actor(Partner),contains(_,Partner))
                        ),
                        compose_message(de,Message,
                        [
                        'Es sollte modelliert werden, welche Art von Abhaengigkeit besteht.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Do not use a Dependency Link without showing the Dependum'
                        ]),
                        message('WARNING',E,Message,'missing_dependum'),
                        set_attribute(E,missing_dependum,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=241').

% A Goal can only be decomposed using Means-Ends Links 
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=210
% see also: goal_decomposition

wrong_arc_to_goal :-    tocheck(wrong_arc_to_goal),
                        goal(G),
                        contribution(_,G),
                        compose_message(de,Message,
                        [
                        'Ein Goal kann nur durch Means-Ends-Links verfeinert werden.'
                        ]),
                        compose_message(en,Message,
                        [
                        'A Goal can only be decomposed using Means-Ends Links.'
                        ]),
                        message('WARNING',G,Message,'wrong_arc_to_goal'),
                        set_attribute(G,wrong_arc_to_goal,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=210').
                        

% To indicate that a Goal can be achieved by performing several sub-tasks, model the decomposition by introducing a Task.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=206

goal_decomposition :-   tocheck(goal_decomposition),
                        goal(G),
                        anddecomposition(_,G),
                        compose_message(de,Message,
                        [
                        'Ein Decomposition-Link kann nicht direkt zu einem Goal fuehren. Hier kann es helfen, eine Task einzufuegen.'
                        ]),
                        compose_message(en,Message,
                        [
                        'To indicate that a Goal can be achieved by performing several sub-tasks, model the decomposition by introducing a Task.'
                        ]),
                        message('ERROR',G,Message,'goal_decomposition'),
                        set_attribute(G,goal_decomposition,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=206').



% Do not extend Decomposition Links beyond the boundaries of actors.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=274

decomposition_link_across_actor_boundaries :-   tocheck(decomposition_link_across_actor_boundaries),
                        element_different_from_actor(E1),
                        anddecomposition(E1,E2),
                        element_different_from_actor(E2),
                        contains(Container1,E1),contains(Container2,E2),
                        Container1 \== Container2,
                        compose_message(de,Message,
                        [
                        'Decomposition-Links sind nur zwischen Elementen innerhalb des selben Akteurs erlaubt.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Do not extend Decomposition Links beyond the boundaries of actors.'
                        ]),
                        message('ERROR',E1,Message,'decomposition_link_across_actor_boundaries'),
                        set_attribute(E1,decomposition_link_across_actor_boundaries,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=274').


% Do not use Dependency Links inside an Actor.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=238

dependency_link_inside_actor :- tocheck(dependency_link_inside_actor),
                        element_different_from_actor(E1),contains(Container,E1),
                        element_different_from_actor(E2),contains(Container,E2),
                        (dependency(E1,Dependum),dependency(Dependum,E2)),
                        compose_message(de,Message,
                        [
                        'Dependency-Links sind nicht zwischen Elementen innerhalb des selben Akteurs erlaubt.'
                        ]),
                        compose_message(en,Message,
                        [
                        ' Do not use Dependency Links inside an Actor.'
                        ]),
                        message('ERROR',E1,Message,'dependency_link_inside_actor'),
                        set_attribute(E1,dependency_link_inside_actor,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=238').
                        
% Connect each Strategic Dependency Link in an SR model to the correct element within the actor
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=267
                        
depencency_link_at_actor_boundary :- tocheck(depencency_link_at_actor_boundary),
                        actor(A),contains(A,_),                       % i.e. we have a SR model
                        (dependency(A,_);dependency(_,A)),
                        compose_message(de,Message,
                        [
                        'Dependency-Links in einem SR-Modell sollten auf die betroffenen Elemente innerhalb des Akteurs verweisen.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Connect each Strategic Dependency Link in an SR model to the correct element within the actor.'
                        ]),
                        message('ERROR',A,Message,'depencency_link_at_actor_boundary'),
                        set_attribute(A,depencency_link_at_actor_boundary,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=267').


% Don’t mix Goals and Tasks in the Means-Ends links.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=208

means_ends_between_goals :-     tocheck(means_ends_between_goals),
                        goal(G1),
                        me(G1,G2),
                        goal(G2),
                        compose_message(de,Message,
                        [
                        'Means-Ends-Links muessen immer zu einer Task fuehren; zwischen Goals sind sie nicht erlaubt.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Means-ends links can lead to a task only, not to a goal.'
                        ]),
                        message('WARNING',G1,Message,'means_ends_between_goals'),
                        set_attribute(G1,means_ends_between_goals,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=208').

% Don’t use Contribution Links between actors.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=288

contribution_link_between_actors :-     tocheck(contribution_link_between_actors),
                        (
                         (actor(X),contribution(X,B),actor(B))                  %actor ->  actor
                         ;
                         (contains(_,X),contribution(X,B),actor(B))    %element within actor ->  actor
                         ;
                         (contains(A,X),contribution(X,Y),contains(B,Y),A \== B)   % element within actor -> element within another actor
                         ;
                         (actor(A),contribution(A,X),contains(_,X))             %actor -> element within actor
                        ),
                        compose_message(de,Message,
                        [
                        'Contribution-Links duerfen nicht zwischen Akteuren stehen.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Don’t use Contribution Links between actors.'
                        ]),
                        message('WARNING',X,Message,'contribution_link_between_actors'),
                        set_attribute(X,contribution_link_between_actors,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=288').


% Don’t use Contribution Links from a Task to a Task.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=289

contribution_link_between_tasks :-      tocheck(contribution_link_between_tasks),
                        task(T1),
                        contribution(T1,T2),
                        task(T2),
                        compose_message(de,Message,
                        [
                        'Contribution-Links duerfen nicht zwischen Tasks stehen.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Don’t use Contribution Links between tasks.'
                        ]),
                        message('WARNING',T1,Message,'contribution_link_between_tasks'),
                        set_attribute(T1,contribution_link_between_tasks,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=289').

% Don’t use the Task Decomposition Link or Means-End Link to refine Softgoals.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=275

wrong_softgoal_refinement :- tocheck(wrong_softgoal_refinement),
                        element(S1),
                        (
                         (me(S1,S2),Linktype= 'Means-Ends')
                        ;
                         (anddecomposition(S1,S2),Linktype='Decomposition')
                        ),
                        softgoal(S2),
                        compose_message(de,Message,
                        [
                        Linktype,
                        '-Links duerfen nicht zwischen Subgoals stehen.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Don’t use ',
                        Linktype,
                        ' Links between subgoals.'
                        ]),
                        message('WARNING',S1,Message,'wrong_softgoal_refinement'),
                        set_attribute(S1,wrong_softgoal_refinement,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=275').

% Ensure that both sides of a Dependency Link point in the same direction.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=239

dependency_link_direction :-    tocheck(dependency_link_direction),
                        element_different_from_actor(Dependum),not(contains(_,Dependum)),
                        (
                         (dependency(Dependum,Partner1),dependency(Dependum,Partner2),Partner1 @< Partner2)
                        ;
                         (dependency(Partner1,Dependum),dependency(Partner2,Dependum),Partner1 @< Partner2)
                        ),
                        compose_message(de,Message,
                        [
                        'Die Dependency-Links (also die D-Symbole) zeigen nicht in die selbe Richtung.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Ensure that both sides of a Dependency Link point in the same direction.'
                        ]),
                        message('WARNING',Dependum,Message,'dependency_link_direction'),
                        set_attribute(Dependum,dependency_link_direction,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=239').
                        

% Do not reuse Dependums in more than one Dependency Relation
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=240
dependum_used_in_more_than_one_dependency :-  tocheck(dependum_used_in_more_than_one_dependency),
                                          element_different_from_actor(Dependum),not(contains(_,Dependum)),
                                          dependency(A1,Dependum),dependency(Dependum,B1),
                                          dependency(A2,Dependum),dependency(Dependum,B2),
                                          (A1 \== A2; B1 \== B2),
                        compose_message(de,Message,
                        [
                        'Fuer jeden Dependency-Link sollte die Abhaengigkeit separat modelliert werden.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Do not reuse Dependums in more than one Dependency Relation.'
                        ]),
                        message('WARNING',Dependum,Message,'dependum_used_in_more_than_one_dependency'),
                        set_attribute(Dependum,dependum_used_in_more_than_one_dependency,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=240').

% Means-Ends are only used to link a Task to a Goal.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=271

means_ends_link_used_wrongly :- tocheck(means_ends_link_used_wrongly),
                        me(E,Partner),
                        (not(task(E));not(goal(Partner))),
                        compose_message(de,Message,
                        [
                        'Means-Ends-Links sind nur von einer Task zu einem Goal erlaubt.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Means-Ends are only used to link a Task to a Goal.'
                        ]),
                        message('WARNING',E,Message,'means_ends_link_used_wrongly'),
                        set_attribute(E,means_ends_link_used_wrongly,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=271').

% Use Contribution Links from any element only to a Softgoal element.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=285

contribution_link_used_wrongly :-       tocheck(contribution_link_used_wrongly),
                        element(E),contribution(_,E),not(softgoal(E)),
                        compose_message(de,Message,
                        [
                        'Contribution-Links sind nur von einem Element zu einem Softgoal erlaubt.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Contribution links are only used to link an element to a softgoal.'
                        ]),
                        message('WARNING',E,Message,'contribution_link_used_wrongly'),
                        set_attribute(E,contribution_link_used_wrongly,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=285').

% Softgoal Dependency should not be met directly by a Goal.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=235

softgoal_depends_on_goal :- tocheck(softgoal_depends_on_goal),
                        softgoal(S),
                        (
                        (dependency(G,S),goal(G))
                        ;
                        (dependency(Dependum,S),not(contains(_,Dependum)),dependency(G,Dependum),goal(G))
                        ),
                        compose_message(de,Message,
                        [
                        'Ein Softgoal darf nie direkt von einem Goal abhaengig sein.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Softgoal Dependency should not be met directly by a Goal.'
                        ]),
                        message('WARNING',S,Message,'softgoal_depends_on_goal'),
                        set_attribute(S,softgoal_depends_on_goal,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=235').

% Softgoals and Goals should be decomposed
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=248
goal_or_softgoal_as_leaf :- tocheck(goal_or_softgoal_as_leaf),
                        (goal(X);softgoal(X)),
                         not(contribution(_,X)),
                         not(dependency(_,X)),
                         not(me(_,X)),
                        compose_message(de,Message,
                        [
                        'Es sollte aufgezeigt werden, wovon die Erfuellung des Goals/Softgoals abhaengig ist.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Softgoals and Goals should be decomposed.'
                        ]),
                        message('WARNING',X,Message,'goal_or_softgoal_as_leaf'),
                        set_attribute(X,goal_or_softgoal_as_leaf,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=248').

% Use 'ISA' and 'Is part of' Association links only between actors of the same type.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=234

association_link_between_different_types :-     tocheck(association_link_between_different_types),
                        (isa(A,B);ispartof(A,B)),
                        (
                        (agent(A),(role(B);position(B)))
                        ;
                        (role(A),(agent(B);position(B)))
                        ;
                        (position(A),(agent(B);role(B)))
                        ),
                        compose_message(de,Message,
                        [
                        'Die Links >>ISA<< und >>Is part of duerfen nur zwischen Akteuren des selbern Typs verwendet werden.<<'
                        ]),
                        compose_message(en,Message,
                        [
                        'Use >>ISA<< and >>Is part of<< Association links only between actors of the same type.'
                        ]),
                        message('WARNING',A,Message,'association_link_between_different_types'),
                        set_attribute(A,association_link_between_different_types,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=234').


association_link_between_different_types :-     tocheck(association_link_between_different_types),
                        (isa(A,B);ispartof(A,B)),
                        clause(actor(B),true),          % i.e. B is a general actor, not an agent, role or position
                        (agent(A);role(A);position(A)),
                        compose_message(de,Message,
                        [
                        'Wenn der genaue Typ des Akteurs (Agent, Position, Rolle) bekannt ist, sollte bevorzugt diese verwendet werden.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Actor is a general actor notation. If its type (agent, position or role) is known, it is recommended to use the more specific type.'
                        ]),
                        message('WARNING',A,Message,'association_link_between_different_types'),
                        set_attribute(A,association_link_between_different_types,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=234').



% Use Agents/Roles instead of actors when the distinction is easily made
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=232
specific_actor_links :- tocheck(specific_actor_links),
                        ((instance(A,_),(agent(A);role(A);position(A)))        % In most cases, instance should be a general actor
                        ;
                        (plays(_,A),clause(actor(A),true))                     % i.e. A is a general actor, not an agent, role or position
                        ;
                        (occupies(_,A),clause(actor(A),true))
                        ),
                        compose_message(de,Message,
                        [
                        'Wenn der genaue Typ des Akteurs (Agent, Position, Rolle) bekannt ist, sollte bevorzugt diese verwendet werden.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Use Agents/Roles instead of actors when the distinction is easily made'
                        ]),
                        message('WARNING',A,Message,'specific_actor_links'),
                        set_attribute(A,specific_actor_links,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=232').
                        
%===============================================================================================
% Layout
%===============================================================================================

% Be consistent with the direction of the Task Decomposition Link between a Task and sub Task.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=272

task_decomposition_direction :-  tocheck(task_decomposition_direction),
                        task(T2),
                        anddecomposition(T1,T2),  % T1 is a decomposition of T2
                        contains(Container,T1),contains(Container,T2), % T1 and T2 belong to the same container.
                        shape(T1,_,Y1,_,_),shape(T2,_,Y2,_,_),integer(Y1),integer(Y2),
                        Y1 #< Y2,       %Y-coordinates --> T1 is above T2
                        elementname(T1,N1),elementname(T2,N2),
                        compose_message(de,Message,
                        [
                        'Das Element >>',
                        N1,
                        '<< sollte sich oertlich unter der Task >>',
                        N2,
                        '<< befinden.'
                        ]),
                        compose_message(en,Message,
                        [
                        'The task decomposition >>',
                        N1,
                        '<< should be situated below the task >>',
                        N2,
                        '<<.'
                        ]),
                        message('WARNING',T1,Message,'task_decomposition_direction'),
                        set_attribute(T1,task_decomposition_direction,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=272').
                        
                        



                        
% Adopt or follow a consistent direction for the goal refinement/decomposition hierarchy.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=268


link_direction_does_not_reflect_hierarchy :- tocheck(link_direction_does_not_reflect_hierarchy),
                        goal(G),
                        me(X,G),  % X is goal refinement for goal G
                        contains(Container,X),contains(Container,G), % X and G belong to the same container.
                        shape(X,_,Y1,_,_),shape(G,_,Y2,_,_),integer(Y1),integer(Y2),
                        Y1 #< Y2,       %Y-coordinates --> X is above G
                        compose_message(de,Message,
                        [
                        'Das uebergeordnete Goal sollte sich oertlich oberhalb seiner untergeordneten Elemente befinden'
                        ]),
                        compose_message(en,Message,
                        [
                        'Adopt or follow a consistent direction for the goal refinement/decomposition hierarchy.'
                        ]),
                        message('WARNING',G,Message,'link_direction_does_not_reflect_hierarchy'),
                        set_attribute(G,link_direction_does_not_reflect_hierarchy,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=268').

% Avoid overlapping elements
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=266
% TODO: Include non-standard element sizes as well as Containers
overlapping_elements :- tocheck(overlapping_elements),
                        element(A),element(B),A @< B,
                        (
                        (contains(C,A),contains(C,B))           % A and B are within the same container
                        ;
                        (not(contains(_,A)),not(contains(_,B)))
                        ),
                        shape(A,XA,YA,XSizeA,YSizeA),shape(B,XB,YB,XSizeB,YSizeB),
                        integer(XA),integer(XB),integer(YA),integer(YB),
                        XDiff is (XA-XB),
                        YDiff is (YA-YB),
                        between(-100,100,XDiff),
                        between(-45,45,YDiff),
                        compose_message(de,Message,
                        [
                        'Ueberlappende Elemente sollten vermieden werden.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Avoid overlapping elements.'
                        ]),
                        message('WARNING',A,Message,'overlapping_elements'),
                        set_attribute(A,link_direction_does_not_reflect_hierarchy,Message).
                        

% Do not draw SR model elements outside the boundaries of the corresponding actor
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=269
% TODO
element_outside_actor_boundaries :- tocheck(element_outside_actor_boundaries),
                        %%%
                        compose_message(de,Message,
                        [
                        'Das Element sollte innerhalb der Grenzen seines Akteurs liegen.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Do not draw SR model elements outside the boundaries of the corresponding actor.'
                        ]),
                        message('WARNING',E,Message,'element_outside_actor_boundaries'),
                        set_attribute(E,element_outside_actor_boundaries,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=269').

% Keep Dependency Links outside the boundaries of Actors
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=202
% TODO
dependum_inside_actor :- tocheck(dependum_inside_actor),
                        %%%
                        compose_message(de,Message,
                        [
                        'Das Element, das die Abhaengigkeit zeigt, sollte sich ausserhalb der Grenzen der Akteure befinden.'
                        ]),
                        compose_message(en,Message,
                        [
                        'Keep Dependency Links outside the boundaries of Actors.'
                        ]),
                        message('WARNING',E,Message,'dependum_inside_actor'),
                        set_attribute(E,dependum_inside_actor,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=202').

%===============================================================================================
% Basic predicates for string operations
%===============================================================================================

string_ends_with(String,Substring) :- sub_string(String,_,_,0,Substring).
string_starts_with(String,Substring) :- sub_string(String,0,_,_,Substring).
string_contains(String,Substring) :- sub_string(String,_,_,_,Substring).

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



replace_all(InputList, From, To, ResultList) :- append(From, After, FromAndAfter),
                                        append(Before, FromAndAfter, InputList),
                                        append(Before, To, BeforeAndTo),
                                        append(BeforeAndTo, FinalAfter, ResultList),
                                        !,
                                        replace_all(After, From, To, FinalAfter).

replace_all(InputList, _, _, InputList).

replacement(Replace,By) :- synonym(Replace,By).
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
synonym(Stopword,' ') :- stopword(Stopword).
stopword('.').
stopword(',').
stopword(';').
stopword('!').

% Adds a space before and after the string, replaces uppercase letters and deletes some special characters:
% X='aB,c' --> SpaceXSpace=' abc '
canonical(X,Normalform) :-      string_to_atom(Space,' '),
                                string_concat(Space,X,SpaceX),
                                string_concat(SpaceX,Space,SpaceXSpace),
                                canon(SpaceXSpace,Normalform).

canon(N,NN) :- (   
                                replacement(X,Y),
                                string_to_atom(Replace,X),string_to_atom(By,Y),
                                replace_substring(N,Replace,By,N2),
                                canon(N2,NN),!
                             );
                              N=NN.
                              
                              

%===============================================================================================
% Rules that are related to the labels of the intentional elements
%===============================================================================================

% Element does not have a name

name_missing :- tocheck(name_missing),
                        elementname(E,''),
                        compose_message(de,Message,
                        [
                        'Das Element hat keinen Namen.'
                        ]),
                        compose_message(en,Message,
                        [
                        'The element does not have a label.'
                        ]),
                        message('ERROR',E,Message,'name_missing').

looks_like_goal :- tocheck(looks_like_goal),
                        language(Language),
                        (task(X);resource(X)),
                        elementname(X,Label),seems_to_be_goal(Language,Label),
                        compose_message(de,Message,
                        [
                        'Sollte moeglicherweise als Goal oder Softgoal modelliert werden: ',
                        Label
                        ]),
                        compose_message(en,Message,
                        [
                        'Consider to use a goal or softgoal for: ',
                        Label
                        ]),
                        message('WARNING',X,Message,'looks_like_goal'),
                        set_attribute(X,looks_like_goal,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=204').

seems_to_be_goal(Language,Label) :- canonical(Label,CLabel),
                        goal_indicator(Language,I),
                        string_contains(CLabel,I).

goal_indicator(en,' be ').
goal_indicator(en,' is ').
goal_indicator(en,' conforms to ').
goal_indicator(en,' must ').
goal_indicator(en,' should ').

% Use a Softgoal for quality criterion and use a (hard) goal for a sharply defined objective.
% http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=204

looks_like_softgoal :- tocheck(looks_like_softgoal),
                        language(Language),
                        (goal(X);task(X);resource(X)),
                        elementname(X,Label),seems_to_be_softgoal(Language,Label),
                        compose_message(de,Message,
                        [
                        'Sollte moeglicherweise als Softgoal modelliert werden: ',
                        Label
                        ]),
                        compose_message(en,Message,
                        [
                        'Consider to use a softgoal for: ',
                        Label
                        ]),
                        message('WARNING',X,Message,'looks_like_softgoal'),
                        set_attribute(X,looks_like_softgoal,'http://istar.rwth-aachen.de/tiki-index.php?page_ref_id=204').

seems_to_be_softgoal(Language,Label) :- canonical(Label,CLabel),
                        not(does_not_look_like_softgoal(Language,CLabel)),
                        softgoal_indicator(Language,I),
                        string_contains(CLabel,I).

seems_to_be_softgoal(en,Label) :- canonical(Label,CLabel),
                        string_contains(CLabel,' in a '),
                        string_contains(CLabel,' manner ').
                        
does_not_look_like_softgoal(Language,Label) :-  canonical(Label,CLabel),
                        softgoal_anti_indicator_startofsentence(Language,I),
                        string_starts_with(CLabel,I).

does_not_look_like_softgoal(Language,Label) :-  canonical(Label,CLabel),
                        softgoal_anti_indicator_endofsentence(Language,I),
                        string_ends_with(CLabel,I).
                        
does_not_look_like_softgoal(Language,Label) :-  canonical(Label,CLabel),
                        softgoal_anti_indicator(Language,I),
                        string_contains(CLabel,I).


% Activity descriptions like "Test Performance" should not be reported as possible softgoal.
softgoal_anti_indicator_startofsentence(en,' perform ').
softgoal_anti_indicator_startofsentence(en,' use ').
softgoal_anti_indicator_startofsentence(en,' add ').
softgoal_anti_indicator_startofsentence(en,' make ').
softgoal_anti_indicator_startofsentence(en,' produce ').
softgoal_anti_indicator_startofsentence(en,' create ').
softgoal_anti_indicator_startofsentence(en,' generate ').
softgoal_anti_indicator_startofsentence(en,' set ').
softgoal_anti_indicator_startofsentence(en,' handle ').
softgoal_anti_indicator_startofsentence(en,' test ').
softgoal_anti_indicator_startofsentence(en,' audit ').
softgoal_anti_indicator_startofsentence(en,' evaluate ').
softgoal_anti_indicator_startofsentence(en,' analyze ').
softgoal_anti_indicator_startofsentence(en,' analyse ').
softgoal_anti_indicator_startofsentence(en,' review ').
softgoal_anti_indicator_startofsentence(en,' inspect ').
softgoal_anti_indicator_startofsentence(en,' investigate ').
softgoal_anti_indicator_startofsentence(en,' select ').
softgoal_anti_indicator_startofsentence(en,' register ').
softgoal_anti_indicator_startofsentence(en,' check ').
softgoal_anti_indicator_startofsentence(en,' collect ').
softgoal_anti_indicator_startofsentence(en,' document ').  % such as "document performance issues"
softgoal_anti_indicator_startofsentence(en,' report ').
softgoal_anti_indicator_startofsentence(en,' plan ').

softgoal_anti_indicator_endofsentence(en,' document ').    % such as "performance document" (resource)
softgoal_anti_indicator_endofsentence(en,' report ').
softgoal_anti_indicator_endofsentence(en,' check ').
softgoal_anti_indicator_endofsentence(en,' test ').
softgoal_anti_indicator_endofsentence(en,' audit ').
softgoal_anti_indicator_endofsentence(en,' investigation ').

softgoal_anti_indicator(en,' tuning ').
softgoal_anti_indicator(en,' analysis ').
softgoal_anti_indicator(en,' inspection ').
softgoal_anti_indicator(en,' less than ').


softgoal_indicator(en,' high ').
softgoal_indicator(en,' higher ').
softgoal_indicator(en,' highest ').
softgoal_indicator(en,' highly ').
softgoal_indicator(en,' more ').
softgoal_indicator(en,' good ').
softgoal_indicator(en,' broad ').
softgoal_indicator(en,' increase ').
softgoal_indicator(en,' improve ').
softgoal_indicator(en,' better ').
softgoal_indicator(en,' low ').
softgoal_indicator(en,' lower ').
softgoal_indicator(en,' lowest ').
softgoal_indicator(en,' less ').
softgoal_indicator(en,' least ').
softgoal_indicator(en,' decrease ').
softgoal_indicator(en,' most ').
softgoal_indicator(en,' maximal ').
softgoal_indicator(en,' maximum ').
softgoal_indicator(en,' maximize ').
softgoal_indicator(en,' maximise ').
softgoal_indicator(en,' maximized ').
softgoal_indicator(en,' maximised ').
softgoal_indicator(en,' minimal ').
softgoal_indicator(en,' minimum ').
softgoal_indicator(en,' minimize ').
softgoal_indicator(en,' minimise ').
softgoal_indicator(en,' minimized ').
softgoal_indicator(en,' minimised ').
softgoal_indicator(en,' performance ').
softgoal_indicator(en,' efficient ').
softgoal_indicator(en,' efficiently ').
softgoal_indicator(en,' efficiency ').
softgoal_indicator(en,' effective ').
softgoal_indicator(en,' effectively ').
softgoal_indicator(en,' effectiveness ').
softgoal_indicator(en,' prize ').
softgoal_indicator(en,' easy ').
softgoal_indicator(en,' easily ').
softgoal_indicator(en,' comfortable ').
softgoal_indicator(en,' comfortably ').
softgoal_indicator(en,' convenient ').
softgoal_indicator(en,' conveniently ').
softgoal_indicator(en,' convenience ').
softgoal_indicator(en,' satisfied ').
softgoal_indicator(en,' satisfaction ').
softgoal_indicator(en,'ability '). % such as usability, availability, configurability
softgoal_indicator(en,' usable ').
softgoal_indicator(en,' happy ').
softgoal_indicator(en,' fast ').
softgoal_indicator(en,' quick ').
softgoal_indicator(en,' quickly ').
softgoal_indicator(en,' short ').
softgoal_indicator(en,' timely ').
softgoal_indicator(en,' on time ').
softgoal_indicator(en,' costly ').      % Note: 'cost(s)' rather sounds like a resource.
softgoal_indicator(en,' inexpensive ').
softgoal_indicator(en,' cheaply ').
softgoal_indicator(en,' profitable ').
softgoal_indicator(en,' reliable ').
softgoal_indicator(en,' reliably ').
softgoal_indicator(en,' reliability ').
softgoal_indicator(en,' be available ').
softgoal_indicator(en,' accurate ').
softgoal_indicator(en,' accurately ').
softgoal_indicator(en,' accuracy ').
softgoal_indicator(en,' safely ').
softgoal_indicator(en,' as possible ').
softgoal_indicator(en,' quality ').
softgoal_indicator(en,' robust ').
softgoal_indicator(en,' robustness ').
softgoal_indicator(en,' integrity ').
softgoal_indicator(en,' platform independen'). % independence and independent
softgoal_indicator(en,' avoid ').


%===========================================================================================================
% Metrics
%===========================================================================================================
metric :- metric_number_of_nodes.


metric_number_of_nodes          :- tocheck(metric_number_of_nodes),
                                findall(N,(element(N)),L),length(L,Len),
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
                                message('INFO','',Message,'metric_number_of_nodes').

