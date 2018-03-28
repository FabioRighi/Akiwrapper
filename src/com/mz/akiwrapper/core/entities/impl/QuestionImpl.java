package com.mz.akiwrapper.core.entities.impl;

import org.json.JSONObject;

import com.mz.akiwrapper.core.entities.CompletionStatus;
import com.mz.akiwrapper.core.entities.CompletionStatus.Level;
import com.mz.akiwrapper.core.entities.Question;
import com.mz.akiwrapper.core.exceptions.MissingQuestionException;

public class QuestionImpl implements Question {

	private final String id;
	private final String question;

	private final int step;

	private final double gain;
	private final double progression;

	/**
	 * Creates a new Question object, used to represent Akinator's question
	 * 
	 * @param parameters
	 *            JSON parameters
	 * @param status
	 *            call completion status
	 * @throws MissingQuestionException
	 *             if the message is missing (no more messages left to answer, get
	 *             the final guesses)
	 * 
	 */
	public QuestionImpl(JSONObject parameters, CompletionStatus status) throws MissingQuestionException {
		if (status.getLevel().equals(Level.WARNING) && status.getReason().toLowerCase().equals("no question"))
			throw new MissingQuestionException();

		this.step = Integer.parseInt(parameters.getString("step"));
		this.question = parameters.getString("question");
		this.progression = Double.parseDouble(parameters.getString("progression"));
		this.id = parameters.getString("questionid");
		this.gain = Double.parseDouble(parameters.getString("infogain"));
	}

	@Override
	public double getProgression() {
		return progression;
	}

	@Override
	public int getStep() {
		return step;
	}

	@Override
	public double getGain() {
		return this.gain; // TODO check if this is real
	}

	@Override
	public String getQuestion() {
		return question;
	}

	@Override
	public String getId() {
		return this.id;
	}

}