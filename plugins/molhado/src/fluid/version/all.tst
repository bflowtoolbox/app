version v(e#2,1);
assert n(1,e#1,2).TestPersistent.val = 2;
version v(e#1,3);
assert n(1,e#1,1).TestPersistent.val = 0;
assert n(1,e#0,4).TestPersistent.val = 1;
version v(e#1,2);
assert n(0,e#0,1).TestPersistent.syntax.Digraph.children[0] = n(1,e#0,2);
assert n(0,e#0,2).TestPersistent.cfg.SymmetricDigraph.parents[0] = n(0,e#0,6);
assert n(1,e#0,4).TestPersistent.cfg.Digraph.children[0] = n(0,e#0,1);
assert n(1,e#1,1).TestPersistent.cfg.EdgeDigraph.isEdge = true;
version v(e#0,1);
assert n(0,e#0,2).TestPersistent.val = 0;
assert n(1,e#0,3).TestPersistent.cfg.SymmetricDigraph.parents[0] = 
       n(0,e#0,7);
assert n(0,e#0,1).TestPersistent.treeChanged{v(e#1,1)} = true;
