package oop.tick5;

import java.io.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class GUILife extends JFrame implements ListSelectionListener {
	private World mWorld = null;
	private PatternStore store;
	private List<World> cachedWorlds = new ArrayList<World>();

	private GamePanel mGamePanel;
	private JButton mPlayButton;
	private java.util.Timer mTimer;
	private boolean mPlaying = false;

	public GUILife(String src) throws IOException {
		this(new PatternStore(src));
	}

	public GUILife(PatternStore ps) {
		super("Game of Life");
		store = ps;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024, 768);

		add(createPatternsPanel(), BorderLayout.WEST);
		add(createControlPanel(), BorderLayout.SOUTH);
		add(createGamePanel(), BorderLayout.CENTER);
	}

	private void addBorder(JComponent component, String title) {
		Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border tb = BorderFactory.createTitledBorder(etch,title);
		component.setBorder(tb);
	}

	private JPanel createGamePanel() {
		mGamePanel = new GamePanel();
		addBorder(mGamePanel, "Game Panel");
		return mGamePanel;
	}

	private JPanel createPatternsPanel() {
		JPanel patt = new JPanel();
		patt.setLayout(new BorderLayout());
		addBorder(patt,"Patterns");
		DefaultListModel<Pattern> model = new DefaultListModel<>();
		for (Pattern p : store.getPatternsNameSorted()) {
			model.addElement(p);
		}
		JList<Pattern> list = new JList<>(model);
		list.addListSelectionListener(this);
		JScrollPane scroll = new JScrollPane(list);
		patt.add(scroll, BorderLayout.CENTER);
		return patt; 
	}

	private JPanel createControlPanel() {
		JPanel ctrl =  new JPanel();
		ctrl.setLayout(new GridLayout(0, 3));
		addBorder(ctrl,"Controls");

		JButton back = new JButton("< Back");
		back.addActionListener(e -> moveBack());
		ctrl.add(back);

		mPlayButton = new JButton("Play");
		mPlayButton.addActionListener(e -> togglePlay());
		ctrl.add(mPlayButton);

		JButton forward = new JButton("Forward >");
		forward.addActionListener(e -> moveForward());
		ctrl.add(forward);

		ActionListener canceler = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mTimer != null) mTimer.cancel();
				mPlayButton.setText("Play");
			}
		};

		back.addActionListener(canceler);
		forward.addActionListener(canceler);

		return ctrl;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<Pattern> list = (JList<Pattern>)e.getSource();
		Pattern p = list.getSelectedValue();
		
		try {
			if (p.getWidth() * p.getHeight() <= 64) {
				mWorld = new PackedWorld(p);
			}
			else {
				mWorld = new ArrayWorld(p);
			}

			cachedWorlds.clear();
			cachedWorlds.add(mWorld);
			mGamePanel.display(mWorld);
		}
		catch (PatternFormatException exp) {
			System.out.println(exp.getMessage());
			mWorld = null;
		}
		
	}

	private void moveBack() {
		if (mWorld != null) {
			if (mWorld.getGenerationCount() < 1) {
				System.out.println("No mWorlds to go back to");
			}
			else {
				mWorld = cachedWorlds.get(mWorld.getGenerationCount() - 1);
				mGamePanel.display(mWorld);
			}
		}
	}

	private void moveForward() {
		if (mWorld != null) {
			try {
				mWorld = cachedWorlds.get(mWorld.getGenerationCount() + 1);
			}
			catch (IndexOutOfBoundsException e) {
				mWorld = copyWorld(true);
				mWorld.nextGeneration();
				cachedWorlds.add(mWorld);
			}
			mGamePanel.display(mWorld);
		}
	}

	private void togglePlay() {
		mPlaying = !mPlaying;
		if (mPlaying) {
			mPlayButton.setText("Stop");
			mTimer = new java.util.Timer(true);
			mTimer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					moveForward();
				}
			}, 0, 500);
		}
		else {
			mTimer.cancel();
			mPlayButton.setText("Play");
		}
	}

	private World copyWorld(boolean clone) {
		if (clone) {
			try {
				return (World)mWorld.clone();
			}
			catch (CloneNotSupportedException e) {
				return null;
			}
		}
		else {
			try {
				return mWorld.getClass().getConstructor(mWorld.getClass()).newInstance(mWorld);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		GUILife gui = new GUILife("http://www.cl.cam.ac.uk/teaching/1617/OOProg/ticks/life.txt");
		gui.setVisible(true);
	}
}